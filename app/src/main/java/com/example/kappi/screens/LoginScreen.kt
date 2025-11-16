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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

class LoginScreen: Screen(R.string.LoginScreen) {
    @Composable
    public fun Renderer(user: User, state: StateEnum, LoginScreen: () -> Unit, homeScreen: () -> Unit, detailScreen: () -> Unit){
        val topBar = TopBar()
        val menuBar = MenuBar()

        LoginContent(user)
        Column()
        {
            topBar.TopBarRenderer(user, this@LoginScreen, state, LoginScreen)
            menuBar.MenuBarRenderer(homeScreen, detailScreen)
        }
    }

    @Composable
    fun LoginContent(user: User){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item { Spacer(modifier = Modifier.padding(80.dp)) }
            item{
                 Text(
                    text = stringResource(R.string.LoginText1),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF93FF97),
                    modifier = Modifier.width(350.dp)
                 )
            }
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item{
                Text(
                    text = stringResource(R.string.LoginText2),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.width(250.dp)
                )
            }
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item { LoginBox(user) }
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item {
                Row(){
                    Button(
                        onClick = {},
                        modifier = Modifier.width(150.dp)
                    ){
                        Text(
                            text = stringResource(R.string.LoginButton),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 38.sp
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
            item { Spacer(modifier = Modifier.padding(80.dp)) }
        }
    }

    @Composable
    fun LoginBox(user: User){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(2.dp, Color.White, RoundedCornerShape(20.dp))
                .background(Color(0xFF7C7881))
                .padding(20.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
            ){
                Row()
                {
                    Image(
                        painter = painterResource(R.drawable.ph_offyellow),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
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
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.width((250.dp))
            )
            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                modifier = Modifier.width(250.dp)
            )
        }
    }
}