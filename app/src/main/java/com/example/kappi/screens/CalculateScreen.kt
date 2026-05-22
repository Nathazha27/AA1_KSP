package com.example.kappi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.NeonDivider
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.ui.theme.LatoFontFamily
import com.example.kappi.ui.theme.PixelifyFontFamily

class CalculateScreen : DetailScreen(R.string.CalculatorScreen) {
    @Composable
    public fun CalculatorRenderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginScreen: () -> Unit
    ){
        val topBar = TopBar()
        val menuBar = MenuBar()
        CalculatorComp()
        Column()
        {
            topBar.TopBarRenderer(user,
                this@CalculateScreen,
                state,
                LoginScreen
            )
            menuBar.MenuBarRenderer(
                onNavigate
            )
        }
    }

    @Composable
    fun CalculatorComp() {
        val num1 = remember { mutableStateOf("") }
        val num2 = remember { mutableStateOf("") }
        val num3 = remember { mutableStateOf("") }
        val num4 = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .width(300.dp)
                .shadow(6.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .border(2.dp, Color(0xFF90709D), RoundedCornerShape(20.dp))
                .background(Color(0xFF1E1C24))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.CalculatorText1),
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color(0xFFEDEDED)
                )
                NeonDivider().Render()
                LoginFormInput(num1)
                Spacer(modifier = Modifier.height(10.dp))
                LoginFormInput(num2)
                Spacer(modifier = Modifier.height(10.dp))
                LoginFormInput(num3)
                Spacer(modifier = Modifier.height(10.dp))
                LoginFormInput(num4)
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginFormInput(valueDef: MutableState<String>) {
        OutlinedTextField(
            value = valueDef.value,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { valueDef.value = it },
            modifier = Modifier.width(250.dp).height(50.dp).background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
            textStyle = TextStyle(
                fontFamily = PixelifyFontFamily,
                fontSize = 20.sp,
                color = Color(0xFF222222),
            )
        )
    }
}