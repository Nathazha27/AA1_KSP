package com.example.kappi.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
        isMenuOpen: Boolean,
        onMenuOpenChange: (Boolean) -> Unit,
        onNavigate: (Screen) -> Unit
    ) {
        //Renderer del menú lateral

        val widthMenu = animateDpAsState(
            targetValue = if (isMenuOpen) 52.dp else 0.dp,
            animationSpec = tween(500),
            label = "MenuAnim"
        )

        val buttonXOffset = animateDpAsState(
            targetValue = if(isMenuOpen) 50.dp else 0.dp,
            animationSpec = tween(500),
            label = "ButtonAnim"
        )

        val menuShape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 10.dp
        )

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.CenterStart
        ){
            Box(
                modifier = Modifier
                    .shadow(
                        6.dp,
                        menuShape,
                        false,
                        Color(0xFFEA00FF),
                        Color(0xFFEA00FF)
                    )
                    .clip(menuShape)
                    .border(2.dp, Color(0xFF90709D), menuShape)
                    .width(widthMenu.value)
                    .background(Color(0xFF24222D)) //Le paso el width dependiendo de si está cerrado o abierto
                    .padding(vertical = 20.dp),
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

            IconButton(
                onClick = {onMenuOpenChange(!isMenuOpen)},
                modifier = Modifier.align(Alignment.BottomEnd).offset(buttonXOffset.value, -20.dp
                )
            ){
                Image(
                    painter = painterResource(R.drawable.ph_onred),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

}