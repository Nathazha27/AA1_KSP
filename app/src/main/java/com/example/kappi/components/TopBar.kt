package com.example.kappi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.screens.Screen
import com.example.kappi.ui.theme.LatoFontFamily

class TopBar {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    public fun TopBarRenderer(user: User, screen: Screen, state: StateEnum, LoginScreen: () -> Unit){
        val userProfile = UserPic()
        val appState = ConnectionState()
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
            ),
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF514C70), Color(0xFF3D2C45))
                )
            ).drawBehind {
                drawLine(
                    color =  Color(0xFF90709D),
                    start = Offset(0f, size.height - 2),
                    end = Offset(size.width, size.height - 2),
                    strokeWidth = 4.0f
                )
            },
            title = {
                Text(
                    text = stringResource(id = screen.screenName),
                    textAlign = TextAlign.Center,
                    fontFamily = LatoFontFamily,
                    color = Color(0xFFEDEDED),
                    fontSize = 35.sp,
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