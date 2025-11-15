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
import androidx.compose.ui.tooling.preview.Preview
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.screens.DetailScreen
import com.example.kappi.screens.HomeScreen
import com.example.kappi.screens.LoginScreen
import com.example.kappi.screens.Screen
import com.example.kappi.ui.theme.KappiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KappiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
    var actualUser by remember {mutableStateOf(User(R.drawable.pic, "Nathazha", "Token"))}
    var actualScreen = remember {mutableStateOf<Screen>(HomeScreen())}

    when (val screen = actualScreen.value) {
        is HomeScreen -> screen.Renderer(actualUser, appState, {actualScreen.value = LoginScreen()})
        is LoginScreen -> screen.Renderer(actualUser, appState, {actualScreen.value = LoginScreen()})
        is DetailScreen -> screen.Renderer(actualUser, appState, {actualScreen.value = LoginScreen()})
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