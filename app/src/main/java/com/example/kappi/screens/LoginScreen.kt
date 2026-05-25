package com.example.kappi.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.NeonDivider
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.ui.theme.LatoFontFamily
import com.example.kappi.ui.theme.PixelifyFontFamily
import com.example.kappi.viewmodels.DashboardViewModel
import com.example.kappi.viewmodels.TopBarViewModel
import androidx.compose.foundation.Image

class LoginScreen : Screen(R.string.LoginScreen) {
    @Composable
    public fun Renderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginAction: () -> Unit,
        viewModel: DashboardViewModel,
        topBarViewModel: TopBarViewModel
    ) {
        val topBar = TopBar()
        val menuBar = MenuBar()

        LoginContent(user, viewModel)

        Column {
            topBar.TopBarRenderer(user, this@LoginScreen, state, LoginAction, topBarViewModel)
            menuBar.MenuBarRenderer(onNavigate)
        }
    }

    @Composable
    fun LoginContent(user: User, viewModel: DashboardViewModel) {
        val context = LocalContext.current

        val onConnectClick = {
            val ip = user.Username.value.trim()
            val portStr = user.Password.value.trim()

            val ipPattern = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$".toRegex()
            val isValidIp = ip.matches(ipPattern)

            val port = portStr.toIntOrNull()
            val isValidPort = port != null && port > 3000 && port <= 65535

            if (!isValidIp) {
                Toast.makeText(context, "Invalid Ip, should be written as X.X.X.X", Toast.LENGTH_SHORT).show()
            } else if (!isValidPort) {
                Toast.makeText(context, "Invalid Port Range or Format", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.updateConfig(ip, port!!)
                Toast.makeText(context, "Connecting...", Toast.LENGTH_SHORT).show()
            }
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item { Spacer(modifier = Modifier.padding(40.dp)) }
            item {
                Text(
                    text = stringResource(R.string.LoginText1),
                    textAlign = TextAlign.Center,
                    fontFamily = LatoFontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF93FF97),
                    modifier = Modifier.width(350.dp)
                )
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 40.dp)) {
                    NeonDivider().Render()
                }
            }
            item {
                Text(
                    text = stringResource(R.string.LoginText2),
                    textAlign = TextAlign.Center,
                    fontFamily = PixelifyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 17.sp,
                    modifier = Modifier.width(250.dp),
                    color = Color(0xFFEDEDED)
                )
            }
            item { Spacer(modifier = Modifier.padding(10.dp)) }

            item { LoginBox(user) }

            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = onConnectClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(180.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF514B6F),
                                        Color(0xFF3D2C45)
                                    )
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFFA6A29A)
                            )
                    ) {
                        Text(
                            text = "CONNECT",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(15.dp))
                }
            }
            item { Spacer(modifier = Modifier.padding(40.dp)) }
        }
    }

    @Composable
    fun LoginBox(user: User) {
        Box(
            modifier = Modifier
                .shadow(
                    6.dp,
                    RoundedCornerShape(20.dp),
                    false,
                    Color(0xFFEA00FF),
                    Color(0xFFEA00FF)
                )
                .clip(RoundedCornerShape(20.dp))
                .border(2.dp, Color(0xFFA6A29A), RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF3D2C45),
                            Color(0xFF514B6F)
                        )
                    )
                )
                .padding(25.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = "BRIDGE SETTINGS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))

                LoginFormInput(label = "BRIDGE IP ADDRESS", input = user.Username)

                Spacer(modifier = Modifier.padding(8.dp))

                LoginFormInput(label = "PORT", input = user.Password)

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginFormInput(label: String, input: MutableState<String>) {
        Column {
            Text(
                text = label,
                fontFamily = PixelifyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF93FF97),
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.padding(3.dp))
            OutlinedTextField(
                value = input.value,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { input.value = it },
                modifier = Modifier
                    .width(250.dp)
                    .height(55.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontFamily = PixelifyFontFamily,
                    fontSize = 18.sp,
                    color = Color(0xFF222222),
                ),
                singleLine = true
            )
        }
    }
}