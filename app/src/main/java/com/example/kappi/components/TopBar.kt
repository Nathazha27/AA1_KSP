package com.example.kappi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.models.State
import com.example.kappi.models.User
import com.example.kappi.screens.Screen

class TopBar {
    @Composable
    public fun TopBarRenderer(user: User, screen: Screen, state: State){
        val userProfile = UserPic()
        val appState = ConnectionState()
        Box(
            modifier = Modifier
                .background(Color(0xFF4a455e))
                .padding(
                    WindowInsets.statusBars.asPaddingValues()
                )
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                userProfile.UserPicRenderer(user = user)
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = stringResource(id = screen.screenName),
                    textAlign = TextAlign.Center,
                    fontSize = 45.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                appState.ConnectionStateRenderer(state = state)
            }
        }
    }
}