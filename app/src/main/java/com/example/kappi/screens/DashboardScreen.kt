package com.example.kappi.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User

class DashboardScreen : DetailScreen(R.string.DashboardScreen){
    @Composable
    public fun DashboardRenderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginScreen: () -> Unit
        )
    {
        val topBar = TopBar()
        val menuBar = MenuBar()
        LockScreenOrientation(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        )

        Column()
        {
            topBar.TopBarRenderer(
                user,
                this@DashboardScreen,
                state,
                LoginScreen
            )
            menuBar.MenuBarRenderer(
                onNavigate
            )
        }
    }

    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(orientation) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = activity.requestedOrientation
            activity.requestedOrientation = orientation
            onDispose {
                activity.requestedOrientation = originalOrientation
            }
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
