package com.example.kappi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import com.example.kappi.components.NeonDivider
import com.example.kappi.ui.theme.LatoFontFamily
import com.example.kappi.ui.theme.PixelifyFontFamily

class LoginScreen: Screen(R.string.LoginScreen) {
    @Composable
    public fun Renderer(user: User, state: StateEnum, LoginScreen: () -> Unit, homeScreen: () -> Unit, detailScreen: () -> Unit, calculatorScreen: () -> Unit){
        val topBar = TopBar()
        val menuBar = MenuBar()

        LoginContent(user)
        Column()
        {
            topBar.TopBarRenderer(user, this@LoginScreen, state, LoginScreen)
            menuBar.MenuBarRenderer(homeScreen, detailScreen, calculatorScreen)
        }
    }

    @Composable
    fun LoginContent(user: User){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item { Spacer(modifier = Modifier.padding(40.dp)) }
            item{
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
                Box(
                    modifier = Modifier.padding(horizontal = 40.dp)
                ){
                    NeonDivider().Render()
                }

            }
            item{
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
                Row(){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .background(
                                brush = Brush.linearGradient(colors = listOf(Color(0xFF514B6F), Color(0xFF3D2C45))),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(0.dp)
                            .border( width = 1.dp, shape =  RoundedCornerShape(10.dp), color = Color(0xFFA6A29A))
                    ) {
                        Text(
                            text = stringResource(R.string.HomeLogin),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            lineHeight = 0.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(30.dp))
                    IconButton(
                        onClick = {}
                    ){
                        Image(
                            painter = painterResource(R.drawable.ph_onyellow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(40.dp)) }
        }
    }

    @Composable
    fun LoginBox(user: User){
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
                        colors = listOf(Color(0xFF3D2C45), Color(0xFF514B6F))
                    )
                )
                .padding(20.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
            ){
                Row()
                {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_left_24),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(R.string.LoginMessage1),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                        modifier = Modifier.width((250.dp))
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(R.string.LoginMessage2),
                    textAlign = TextAlign.Left,
                    fontFamily = PixelifyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.width((250.dp))
                )
                Spacer(modifier = Modifier.padding(10.dp))
                LoginFormInput(R.string.LoginUsername, user.Username)
                Spacer(modifier = Modifier.padding(5.dp))
                LoginFormInput(R.string.LoginPassword, user.Password)
                Spacer(modifier = Modifier.padding(5.dp))
                LoginFormInput(R.string.LoginConnection, user.Token)
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginFormInput(type: Int, input: MutableState<String>){
        Column(){
            Text(
                text = stringResource(type),
                textAlign = TextAlign.Left,
                fontFamily = PixelifyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.width((250.dp))
            )
            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                value = input.value,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { input.value = it },
                modifier = Modifier.width(250.dp).height(50.dp).background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontFamily = PixelifyFontFamily,
                    fontSize = 20.sp,
                    color = Color(0xFF222222),
                )
            )
        }
    }
}