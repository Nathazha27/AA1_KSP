package com.example.kappi.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

    @Composable
    public fun VerticalProgressBarRenderer(
        progress: Float,
        modifier: Modifier = Modifier,
        color: Color = Color(0xFF1E1C24),
        trackColor: Color = Color(0xFF1E1C24),
        cornerRadius: Dp = 10.dp
    ) {
        val cornerShape = RoundedCornerShape(10.dp)
        val borderColor = Color(0xFF90709D)
        val neonColor = Color(0xFFEA00FF)
        Box(
            modifier = modifier
                .shadow(
                    elevation = 6.dp,
                    shape = cornerShape,
                    clip = false,
                    ambientColor = neonColor,
                    spotColor = neonColor
                )
                .clip(RoundedCornerShape(cornerRadius))
                .background(color)
                .border(2.dp, borderColor, RoundedCornerShape(cornerRadius))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                val progressHeight = height * progress.coerceIn(0f, 1f)

                if (progressHeight > 0f) {

                    drawRect(
                        color = trackColor,
                        topLeft = Offset(0f, height - progressHeight),
                        size = Size(width, progressHeight)
                    )
                }
            }
        }
    }
