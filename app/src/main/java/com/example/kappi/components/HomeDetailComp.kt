package com.example.kappi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kappi.R
import com.example.kappi.models.HomeDataDetail

class HomeDetailComp {
    @Composable
    public fun RenderHomeComp(homeDetail: HomeDataDetail){
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
                .clickable { homeDetail.onClick() }
                .background(Color(0xFF1E1C24))
                .padding(20.dp, 10.dp)
                .width(300.dp),
            contentAlignment = Alignment.Center
        ){
            Column {
                Text(
                    text = stringResource(homeDetail.title),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                )
                NeonDivider().Render()
                Text(
                    text = stringResource(homeDetail.info),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
    }
}