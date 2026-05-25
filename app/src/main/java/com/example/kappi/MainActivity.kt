package com.example.kappi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kappi.data.TelemetryRepo
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.screens.CalculateScreen
import com.example.kappi.screens.DashboardScreen
import com.example.kappi.screens.DetailScreen
import com.example.kappi.screens.HomeScreen
import com.example.kappi.screens.LoginScreen
import com.example.kappi.screens.Screen
import com.example.kappi.ui.theme.KappiTheme
import com.example.kappi.viewmodels.DashboardViewModel
import com.example.kappi.viewmodels.TopBarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.parseColor("#26222e")
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        TelemetryRepo.startConnection()

        setContent {
            KappiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF26222E)
                ) {
                    KappiApp("KAPPI")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TelemetryRepo.stopConnection()
    }
}

@Composable
fun KappiApp(name: String, modifier: Modifier = Modifier) {
    val telemetryViewModel : DashboardViewModel = viewModel()
    val topBarViewModel : TopBarViewModel = viewModel()

    var appState by remember {mutableStateOf(StateEnum.OFF)}
    var actualUser by remember {mutableStateOf(User(R.drawable.pic, mutableStateOf("192.168.0.0"), mutableStateOf("27415"),mutableStateOf("Token")))}
    var actualScreen = remember {mutableStateOf<Screen>(HomeScreen())}
    var isMenuOpen by remember {mutableStateOf(false)}
    val navigate: (Screen) -> Unit = { nextScreen ->
        actualScreen.value = nextScreen
    }

    when (val screen = actualScreen.value) {
        is HomeScreen -> screen.Renderer(
            actualUser,
            appState,
            navigate,
            {actualScreen.value = LoginScreen()},
            {actualScreen.value = DetailScreen(R.string.DetailScreen)},
            topBarViewModel,
            isMenuOpen,
            {value -> isMenuOpen = value}
        )
        is LoginScreen -> screen.Renderer(
            actualUser,
            appState,
            navigate,
            {actualScreen.value = LoginScreen()},
            telemetryViewModel,
            topBarViewModel,
            isMenuOpen,
            {value -> isMenuOpen = value}
        )
        is DetailScreen -> screen.Renderer(
            actualUser,
            appState,
            navigate,
            {actualScreen.value = LoginScreen()},
            topBarViewModel,
            isMenuOpen,
            {value -> isMenuOpen = value}
        )
        is CalculateScreen -> screen.CalculatorRenderer(
            actualUser,
            appState,
            navigate,
            {actualScreen.value = LoginScreen()},
            topBarViewModel,
            isMenuOpen,
            {value -> isMenuOpen = value}
        )
        is DashboardScreen -> screen.DashboardRenderer(
            actualUser,
            appState,
            navigate,
            {actualScreen.value = LoginScreen()},
            telemetryViewModel,
            topBarViewModel,
            isMenuOpen,
            {value -> isMenuOpen = value}
        )
        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun KappiAppPreview() {
    KappiTheme {
        KappiApp("KAPPI")
    }
}