package com.example.kappi.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kappi.R
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.ui.theme.PixelifyFontFamily
import com.example.kappi.viewmodels.DashboardViewModel
import org.example.proto.KspTelemetry
import org.example.proto.kspTelemetry

class DashboardScreen : Screen(R.string.DashboardScreen) {
    @Composable
    public fun DashboardRenderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginScreen: () -> Unit
    ) {
        val viewModel: DashboardViewModel = viewModel()
        val isConnected by viewModel.isConnected.collectAsState()
        val telemetry by viewModel.telemetry.collectAsState()

        val topBar = TopBar()
        val menuBar = MenuBar()

        LockScreenOrientation(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        )

        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Box(modifier = Modifier.fillMaxSize()) {

            if (isLandscape) {
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp, start = 52.dp)
                ) {
                    LeftLayout(
                        modifier = Modifier
                            .weight(0.125f)
                            .fillMaxHeight()
                    )

                    CenterLayout(
                        modifier = Modifier
                            .weight(0.75f)
                            .fillMaxHeight(),
                        telemetry
                    )

                    RightLayout(
                        modifier = Modifier
                            .weight(0.125f)
                            .fillMaxHeight()
                    )
                }
            }

        if (!isConnected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "NO SIGNAL",
                        color = Color.Red,
                        style = TextStyle(
                            fontFamily = PixelifyFontFamily,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "CHECKING BRIDGE CONNECTION...",
                        color = Color.White,
                        fontFamily = PixelifyFontFamily,
                        fontSize = 14.sp
                    )
                }
            }
        }

            Column {
                topBar.TopBarRenderer(
                    user,
                    this@DashboardScreen,
                    state,
                    LoginScreen
                )
                menuBar.MenuBarRenderer(
                    onNavigate
                )
            }
    }

    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(orientation) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = activity.requestedOrientation
            activity.requestedOrientation = orientation
            onDispose {
                activity.requestedOrientation = originalOrientation
            }
        }
    }

    @Composable
    fun LeftLayout(modifier: Modifier) {
        Column(
            modifier = modifier.background(Color.Red)
        ) {
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Yellow)
            ) {

            }

            Row(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Cyan)
            ) {

            }
        }
    }

    @Composable
    fun CenterLayout(modifier: Modifier, telemetry: KspTelemetry) {
        Column(
            modifier = modifier.background(Color(0xFF1E1C24))
        ) {
            TopCenterLayout(
                modifier = Modifier
                    .weight(.3f)
                    .fillMaxSize()
            )

            MainCenterLayout(
                modifier = Modifier
                    .weight(.7f)
                    .fillMaxSize(),
                telemetry
            )
        }
    }

    @Composable
    fun TopCenterLayout(modifier: Modifier){
        Row(
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .background(Color.Magenta)
        ){

        }
    }

    @Composable
    fun MainCenterLayout(modifier: Modifier, telemetry: KspTelemetry) {
        val cornerShape = RoundedCornerShape(10.dp)
        val backgroundColor = Color(0xFF1E1C24)
        val borderColor = Color(0xFF90709D)
        val neonColor = Color(0xFFEA00FF)


            Column(
                modifier = modifier.padding(10.dp)
            ) {

               Row(modifier = Modifier
                   .weight(0.5f)
                   .fillMaxWidth()) {
                    KappiDashboardBox(
                        Modifier.weight(0.5f),neonColor,cornerShape,backgroundColor,borderColor
                    ){
                        TelemetryRow("Apoapsis:", "${String.format("%,.0f", telemetry.apoapsis)} m")
                        TelemetryRow("Periapsis:", "${String.format("%,.0f", telemetry.periapsis)} m")
                        TelemetryRow("Eta-Pe:", "${String.format("%.0f", telemetry.etaAp)} s")
                        TelemetryRow("Eta-Ap:", "${String.format("%.0f", telemetry.etaPe)} s")
                 }

                     KappiDashboardBox(
                        Modifier.weight(0.5f),neonColor,cornerShape,backgroundColor,borderColor
                    ){
                         TelemetryRow("Alt.(Terr):", "${String.format("%,.0f", telemetry.altTerrain)} m")
                         TelemetryRow("Alt.(Sea):", "${String.format("%,.0f", telemetry.altSeaLevel)} m")
                         TelemetryRow("Planet:", telemetry.currentPlanet)
                         TelemetryRow("Inclination:", "${String.format("%.2f", telemetry.inclination)}°")
                     }
                }

                Row(modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()) {
                    KappiDashboardBox(
                        Modifier.weight(0.65f),neonColor,cornerShape,backgroundColor,borderColor
                    ){
                        TelemetryRow("Vel.(Surf):", "${String.format("%,.1f", telemetry.velSurface)} m/s")
                        TelemetryRow("Vel.(Orb):", "${String.format("%,.1f", telemetry.velOrbit)} m/s")
                        TelemetryRow("Vel.(Horiz):", "${String.format("%,.1f", telemetry.velHComponent)} m/s")
                        TelemetryRow("Vel.(Vert):", "${String.format("%,.1f", telemetry.velVComponent)} m/s")
                    }

                    KappiDashboardBox(
                        Modifier.weight(0.35f),neonColor,cornerShape,backgroundColor,borderColor
                    ){
                        TelemetryRow("TWR:", String.format("%.2f", telemetry.twr))
                        TelemetryRow("Kerbals:", telemetry.kerbalsInVessel.toString())
                        TelemetryRow("G-Force:", String.format("%.2f", telemetry.gForce))
                        TelemetryRow("T+ Time:", "${String.format("%.0f", telemetry.timeElapsed)} s")
                    }

                }
            }
        }
    }

    @Composable
    fun KappiDashboardBox(
        modifier: Modifier,
        shadowColor: Color,
        shape: RoundedCornerShape,
        bg: Color,
        border: Color,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = modifier
                .padding(1.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = shape,
                    clip = false,
                    ambientColor = shadowColor,
                    spotColor = shadowColor
                )
                .clip(shape)
                .border(2.dp, border, shape)
                .background(bg)
                .padding(
                    horizontal = 15.dp,
                    vertical = 2.dp
                )
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ){

                content()
            }
        }
    }


    @Composable
    fun TelemetryRow(label: String, value: String) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    color = Color.Gray,
                    fontFamily = PixelifyFontFamily,
                    fontSize = 12.sp
                )
                Text(
                    text = value,
                    color = Color.White,
                    fontFamily = PixelifyFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(.5.dp))

            Divider(
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .shadow(2.dp, RectangleShape, false, Color(0xFFEA00FF), Color(0xFFEA00FF))
                    .clip(RoundedCornerShape(2.dp)),
                thickness = 1.dp,
                color = Color(0xFF90709D)
            )
        }
    }
    @Composable
    fun RightLayout(modifier: Modifier) {
        Column(
            modifier = modifier.background(Color.Blue)
        ) {
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Yellow)
            ) {
                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 5.dp)
                        .background(Color.Green)
                        ){

                }

                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 5.dp)
                        .background(Color.Magenta)
                ){

                }

                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 5.dp)
                        .background(Color.Cyan)
                ){

                }


            }

            Row(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(Color.Magenta)
            ) {

            }
        }

}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
