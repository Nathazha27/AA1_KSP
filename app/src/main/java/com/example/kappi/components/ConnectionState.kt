package com.example.kappi.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.kappi.R
import com.example.kappi.models.StateEnum
import com.example.kappi.models.StateImg

class ConnectionState {
    @Composable
    public fun ConnectionStateRenderer(state: StateEnum){
        var stateImg = StateImg(R.drawable.ph_ongreen, R.drawable.ph_offgreen, R.drawable.ph_onyellow, R.drawable.ph_offyellow, R.drawable.ph_onred, R.drawable.ph_offred)
        Column(
            modifier = Modifier.padding(10.dp)
        ){
            Image(
                painter =  painterResource(
                    if (state != StateEnum.ON) stateImg.off_onState
                    else stateImg.on_onState),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(
                    if (state != StateEnum.CONNECTING) stateImg.off_connectingState
                    else stateImg.on_connectingState),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(
                    if (state != StateEnum.OFF) stateImg.off_offState
                    else stateImg.on_offState),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
        }
    }
}