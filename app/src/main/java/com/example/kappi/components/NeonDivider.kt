package com.example.kappi.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

class NeonDivider {
    @Composable
    public fun Render(){
        Divider(
            Modifier
                .padding(vertical = 10.dp)
                .shadow(2.dp, RectangleShape, false, Color(0xFFEA00FF), Color(0xFFEA00FF))
                .clip(
                    RoundedCornerShape(2.dp)
                ),
            thickness = 2.dp,
            color = Color(0xFF90709D)
        )
    }
}