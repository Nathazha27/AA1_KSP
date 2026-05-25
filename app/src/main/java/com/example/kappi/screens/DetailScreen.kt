package com.example.kappi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.viewmodels.TopBarViewModel

open class DetailScreen(detailName:Int): Screen(detailName) {
    @Composable
    public fun Renderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginScreen: () -> Unit,
        topBarViewModel: TopBarViewModel,
        isMenuOpen: Boolean,
        onMenuOpenChange: (Boolean) -> Unit
    ){
        val topBar = TopBar()
        val menuBar = MenuBar()
        Column()
        {
            topBar.TopBarRenderer(user,
                this@DetailScreen,
                state,
                LoginScreen,
                topBarViewModel
            )
            menuBar.MenuBarRenderer(isMenuOpen, onMenuOpenChange, onNavigate)
        }
    }
}