package com.example.kappi.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
    public fun VerticalAltimeterRenderer(
        progress: Float,
        modifier: Modifier = Modifier,
        baseBlue: Color = Color(0xFF00E5FF),
        trackColor: Color = Color(0xFF1E1C24),
        cornerRadius: Dp = 10.dp
    ) {
        val limit1 = 25000f / 70000f
        val limit2 = 50000f / 70000f
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
                .background(trackColor)
                .border(2.dp, borderColor, RoundedCornerShape(cornerRadius))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                drawRect(
                    color = baseBlue.copy(alpha = 0.15f),
                    size = Size(width, height * (1f - limit2))
                )

                drawRect(
                    color = baseBlue.copy(alpha = 0.35f),
                    topLeft = Offset(0f, height * (1f - limit2)),
                    size = Size(width, height * (limit2 - limit1))
                )

                drawRect(
                    color = baseBlue.copy(alpha = 0.6f),
                    topLeft = Offset(0f, height * (1f - limit1)),
                    size = Size(width, height * limit1)
                )

                val lineOpacity = 0.3f
                val lineThickness = 2.dp.toPx()

                drawLine(
                    color = Color.White.copy(alpha = lineOpacity),
                    start = Offset(0f, height * (1f - limit1)),
                    end = Offset(width, height * (1f - limit1)),
                    strokeWidth = lineThickness
                )

                drawLine(
                    color = Color.White.copy(alpha = lineOpacity),
                    start = Offset(0f, height * (1f - limit2)),
                    end = Offset(width, height * (1f - limit2)),
                    strokeWidth = lineThickness
                )

                val progressHeight = height * progress.coerceIn(0f, 1f)

                if (progressHeight > 0f) {
                    drawRect(
                        color = Color.White.copy(alpha = 0.5f),
                        topLeft = Offset(0f, height - progressHeight),
                        size = Size(width, progressHeight)
                    )
                }
            }
        }
    }
