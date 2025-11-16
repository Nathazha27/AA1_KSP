package com.example.kappi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User

class DetailScreen: Screen(R.string.DetailScreen) {
    @Composable
    public fun Renderer(user: User, state: StateEnum, LoginScreen: () -> Unit, homeScreen: () -> Unit, detailScreen: () -> Unit){
        val topBar = TopBar()
        val menuBar = MenuBar()
        Column()
        {
            topBar.TopBarRenderer(user, this@DetailScreen, state, LoginScreen)
            menuBar.MenuBarRenderer(homeScreen, detailScreen)
        }
    }
}