package com.example.kappi.components

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
import com.example.kappi.models.State
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding

class ConnectionState {
    @Composable
    public fun ConnectionStateRenderer(state: State){
        Column(
            modifier = Modifier.padding(10.dp)
        ){
            Image(
                painter = painterResource(state.onState),
                contentDescription = null,
                modifier = Modifier.size(15.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(state.connectingState),
                contentDescription = null,
                modifier = Modifier.size(15.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(state.offState),
                contentDescription = null,
                modifier = Modifier.size(15.dp).clip(CircleShape)
            )
        }
    }
}