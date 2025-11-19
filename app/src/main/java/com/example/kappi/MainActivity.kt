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
import com.example.kappi.components.AppBars
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.screens.CKANScreen
import com.example.kappi.screens.CalculateScreen
import com.example.kappi.screens.DetailScreen
import com.example.kappi.screens.HomeScreen
import com.example.kappi.screens.LoginScreen
import com.example.kappi.screens.Screen
import com.example.kappi.screens.TelemetryScreen
import com.example.kappi.screens.TutorialHubScreen
import com.example.kappi.ui.theme.KappiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KappiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF26222E)
                ) {
                    KappiApp("KAPPI")
                }
            }
        }
    }
}

@Composable
fun KappiApp(name: String, modifier: Modifier = Modifier) {
    var appState by remember {mutableStateOf(StateEnum.OFF)}
    var actualUser by remember {mutableStateOf(User(R.drawable.pic, mutableStateOf("Nathazha"), mutableStateOf(""),mutableStateOf("Token")))}
    var actualScreen = remember {mutableStateOf<Screen>(HomeScreen())}
    val topBar = TopBar()
    val menuBar = MenuBar()
    val appBars = AppBars()

    when (val screen = actualScreen.value) {
        is HomeScreen ->
            screen.Renderer(
                { actualScreen.value = LoginScreen() },
                { actualScreen.value = DetailScreen(R.string.DetailScreen) },
                { actualScreen.value = CalculateScreen()},
                { actualScreen.value = TelemetryScreen() },
                { actualScreen.value = TutorialHubScreen() },
                { actualScreen.value = CKANScreen() })
        is LoginScreen ->
            screen.Renderer(actualUser)
        is DetailScreen -> {
        }
        is CalculateScreen ->
            screen.CalculatorRenderer()
        is TelemetryScreen ->
            screen.TelemetryRenderer()
        is TutorialHubScreen ->
            screen.TutorialHubRenderer()
        is CKANScreen ->
            screen.CKANRenderer()
        else -> Unit
    }
    appBars.AppBarsRender(
        actualUser,
        actualScreen.value,
        topBar,
        menuBar,
        appState,
        { actualScreen.value = LoginScreen() },
        { actualScreen.value = HomeScreen() },
        { actualScreen.value = DetailScreen(R.string.DetailScreen) },
        { actualScreen.value = CalculateScreen() },
        { actualScreen.value = TelemetryScreen() },
        { actualScreen.value = TutorialHubScreen() },
        { actualScreen.value = CKANScreen() })
}

@Preview(showBackground = true)
@Composable
fun KappiAppPreview() {
    KappiTheme {
        KappiApp("KAPPI")
    }
}