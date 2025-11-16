package com.example.kappi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kappi.R
import com.example.kappi.models.StateEnum

class MenuBar {
    //Menú lateral izquierdo con botón que lo desplazará de fuera a dentro y de dentro a fuera
    @Composable
    public fun MenuBarRenderer(homeScreen: () -> Unit, detailScreen: () -> Unit){ //Renderer del menú lateral
        Box(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFF24222D)).width(52.dp).padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ){
          Column(
              verticalArrangement = Arrangement.spacedBy(8.dp),
              horizontalAlignment = Alignment.CenterHorizontally
          ){
              IconButton(
                  onClick = homeScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
              IconButton(
                  onClick = detailScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
              IconButton(
                  onClick = detailScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
              IconButton(
                  onClick = detailScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
              IconButton(
                  onClick = detailScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
              IconButton(
                  onClick = detailScreen
              ) {
                  Image(
                      painter = painterResource(R.drawable.ph_offgreen),
                      contentDescription = null,
                      modifier = Modifier.size(50.dp).clip(CircleShape)
                  )
              }
          }
        }
    }

}