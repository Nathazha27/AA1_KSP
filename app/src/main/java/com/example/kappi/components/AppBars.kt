package com.example.kappi.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.screens.Screen

class AppBars {
    @Composable
    public fun AppBarsRender(user: User, screen: Screen, topBar: TopBar, menuBar: MenuBar, state: StateEnum, LoginScreen: () -> Unit, homeScreen: () -> Unit, detailScreen: () -> Unit, calculatorScreen: () -> Unit){
        Column()
        {
            topBar.TopBarRenderer(user, screen, state, LoginScreen)
            menuBar.MenuBarRenderer(homeScreen, detailScreen, calculatorScreen)
        }
    }
}