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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kappi.R
import com.example.kappi.models.StateEnum
import com.example.kappi.screens.CalculateScreen
import com.example.kappi.screens.DashboardScreen
import com.example.kappi.screens.DetailScreen
import com.example.kappi.screens.HomeScreen
import com.example.kappi.screens.Screen

class MenuBar {
    //Menú lateral izquierdo con botón que lo desplazará de fuera a dentro y de dentro a fuera
    @Composable
    public fun MenuBarRenderer(
        onNavigate: (Screen) -> Unit
    ) { //Renderer del menú lateral
        Box(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFF24222D)).width(52.dp).padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ){
          Column(
              verticalArrangement = Arrangement.spacedBy(8.dp),
              horizontalAlignment = Alignment.CenterHorizontally
          ){
              val homeEnabled : Boolean = true
              IconButton(
                  onClick = { onNavigate(HomeScreen()) },
                  enabled = homeEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.baseline_home_24),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (homeEnabled) 1f
                              else .25f
                          )
                  )
              }

              val calculatorEnabled : Boolean = false
              IconButton(
                  onClick = { onNavigate(CalculateScreen()) },
                  enabled = calculatorEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.baseline_calculate_24),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (calculatorEnabled) 1f
                              else .25f
                          )
                  )
              }

              val dashboardEnabled : Boolean = true
              IconButton(
                  onClick = { onNavigate(DashboardScreen()) },
                  enabled = dashboardEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.baseline_speed_24),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (dashboardEnabled) 1f
                              else .25f
                          )
                  )
              }

              val tutorialEnabled : Boolean = false
              IconButton(
                  onClick = { onNavigate(DetailScreen(R.string.DetailScreen)) },
                  enabled = tutorialEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.baseline_menu_book_24),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (tutorialEnabled) 1f
                              else .25f
                          )
                  )
              }

              val ckanEnabled : Boolean = false
              IconButton(
                  onClick = { onNavigate(DetailScreen(R.string.DetailScreen)) },
                  enabled = ckanEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.ckan),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (ckanEnabled) 1f
                              else .25f
                          )
                  )
              }

              val settingsEnabled : Boolean = false
              IconButton(
                  onClick = { onNavigate(DetailScreen(R.string.DetailScreen)) },
                  enabled = settingsEnabled
              ) {
                  Image(
                      painter = painterResource(R.drawable.baseline_settings_24),
                      contentDescription = null,
                      modifier = Modifier
                          .size(50.dp)
                          .clip(CircleShape)
                          .alpha(
                              if (settingsEnabled) 1f
                              else .25f
                          )
                  )
              }
          }
        }
    }

}