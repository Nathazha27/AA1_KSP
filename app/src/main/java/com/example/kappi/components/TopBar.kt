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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.models.StateEnum
import com.example.kappi.models.StateImg
import com.example.kappi.models.User
import com.example.kappi.screens.Screen

class TopBar {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    public fun TopBarRenderer(user: User, screen: Screen, state: StateEnum, LoginScreen: () -> Unit){
        val userProfile = UserPic()
        val appState = ConnectionState()
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF4a455e),
            titleContentColor = Color.White
            ),
            title = {
                Text(
                    text = stringResource(id = screen.screenName),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                userProfile.UserPicRenderer(user, LoginScreen)
            },
            actions = {
                appState.ConnectionStateRenderer(state)
            }
        )
    }
}