package com.example.kappi.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.kappi.R


@Composable
fun BatteryIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF45FF19)
) {
    val batteryPainter = painterResource(id = R.drawable.baseline_battery_full_24)

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.49f }
        ) {
            with(batteryPainter) {
                draw(
                    size = size,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }

            val progressHeight = size.height * progress.coerceIn(0f, 1f)

            drawRect(
                color = activeColor,
                topLeft = Offset(x = 0f, y = size.height - progressHeight),
                size = Size(
                    width = size.width,
                    height = progressHeight
                ),
                blendMode = BlendMode.SrcIn
            )
        }
    }
}

