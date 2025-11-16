package com.example.kappi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.R
import com.example.kappi.classes.HomeDetailLoader
import com.example.kappi.components.HomeDetailComp
import com.example.kappi.components.MenuBar
import com.example.kappi.components.NeonDivider
import com.example.kappi.components.TopBar
import com.example.kappi.models.HomeDataDetail
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User

class HomeScreen: Screen(R.string.HomeScreen) {
    @Composable
    public fun Renderer(user: User, state: StateEnum, LoginScreen: () -> Unit, homeScreen: () -> Unit, detailScreen: () -> Unit){
        val topBar = TopBar()
        val menuBar = MenuBar()
        val detailHome = HomeDetailLoader().LoadHomeDetails(detailScreen)
        HomeContent(LoginScreen, detailHome)
        Column()
        {
            topBar.TopBarRenderer(user, this@HomeScreen, state, LoginScreen)
            menuBar.MenuBarRenderer(homeScreen, detailScreen)
        }
    }

    @Composable
    fun HomeContent(LoginScreen: () -> Unit, detailHome: List<HomeDataDetail>){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item { Spacer(modifier = Modifier.padding(80.dp)) }
            item{
                Text(
                    text = stringResource(R.string.HomeText1),
                    textAlign = TextAlign.Center,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            item { Spacer(modifier = Modifier.padding(2.dp)) }
            item{
                Text(
                    text = stringResource(R.string.HomeText2),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color(0xFF93FF97)
                )
            }
            item { Spacer(modifier = Modifier.padding(2.dp)) }
            item{
                Text(
                    text = stringResource(R.string.HomeText3),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item {
                Button(
                    onClick = LoginScreen,
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = stringResource(R.string.HomeLogin),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            item{HomeInfo()}
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            items(detailHome){
                detail -> HomeDetailComp().RenderHomeComp(detail)
            }
            item { Spacer(modifier = Modifier.padding(80.dp)) }
        }
    }

    @Composable
    fun HomeInfo(){
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
                .border(2.dp, Color(0xFF90709D), RoundedCornerShape(20.dp))
                .background(Color(0xFF1E1C24))
                .padding(20.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ){
            Column{
                Text(
                    text = stringResource(R.string.HomeInfo1),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                )
                NeonDivider().Render()
                Text(
                    text = stringResource(R.string.HomeInfo2),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
                NeonDivider().Render()
                Image(
                    painter = painterResource(R.drawable.diagramkappi),
                    contentDescription = null,
                    modifier = Modifier
                        .width(400.dp)
                        .aspectRatio(2.0f),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}