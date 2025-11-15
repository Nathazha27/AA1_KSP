package com.example.kappi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kappi.R
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.StateImg
import com.example.kappi.models.User

class HomeScreen: Screen(R.string.HomeScreen) {
    @Composable
    public fun Renderer(user: User, state: StateEnum, LoginScreen: () -> Unit){
        val topBar = TopBar()
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            topBar.TopBarRenderer(user, this@HomeScreen, state, LoginScreen)
        }
    }
}