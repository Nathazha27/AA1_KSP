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
import com.example.kappi.R
import com.example.kappi.models.StateEnum

class ConnectionState {
    @Composable
    public fun ConnectionStateRenderer(state: StateEnum){
        Column(
            modifier = Modifier.padding(10.dp)
        ){
            Image(
                painter =  painterResource(
                    if (state != StateEnum.ON) R.drawable.ph_offgreen
                    else R.drawable.ph_ongreen),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(
                    if (state != StateEnum.CONNECTING) R.drawable.ph_offyellow
                    else R.drawable.ph_onyellow),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(
                    if (state != StateEnum.OFF) R.drawable.ph_offred
                    else R.drawable.ph_onred),
                contentDescription = null,
                modifier = Modifier.size(10.dp).clip(CircleShape)
            )
        }
    }
}