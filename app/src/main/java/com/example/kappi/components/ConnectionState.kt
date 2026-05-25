package com.example.kappi.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.example.kappi.R
import com.example.kappi.models.StateEnum

class ConnectionState {
    @Composable
    fun ConnectionStateRenderer(state: StateEnum) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(
                    if (state == StateEnum.ON) R.drawable.ph_ongreen else R.drawable.ph_offgreen
                ),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape),
                colorFilter = if (state == StateEnum.ON) null else ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
            )

            Spacer(modifier = Modifier.size(4.dp))

            Image(
                painter = painterResource(
                    if (state == StateEnum.CONNECTING) R.drawable.ph_onyellow else R.drawable.ph_offyellow
                ),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape),
                colorFilter = if (state == StateEnum.CONNECTING) null else ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
            )

            Spacer(modifier = Modifier.size(4.dp))

            Image(
                painter = painterResource(
                    if (state == StateEnum.OFF) R.drawable.ph_onred else R.drawable.ph_offred
                ),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape),
                colorFilter = if (state == StateEnum.OFF) null else ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
            )
        }
    }
}