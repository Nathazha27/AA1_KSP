package com.example.kappi.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kappi.R
import com.example.kappi.components.BatteryIndicator
import com.example.kappi.components.MenuBar
import com.example.kappi.components.TopBar
import com.example.kappi.components.VerticalAltimeterRenderer
import com.example.kappi.components.VerticalProgressBarRenderer
import com.example.kappi.models.StateEnum
import com.example.kappi.models.User
import com.example.kappi.ui.theme.PixelifyFontFamily
import com.example.kappi.viewmodels.DashboardViewModel
import com.example.kappi.viewmodels.TopBarViewModel
import org.example.proto.KspTelemetry
import org.example.proto.kspTelemetry

class DashboardScreen : Screen(R.string.DashboardScreen) {
    @Composable
    public fun DashboardRenderer(
        user: User,
        state: StateEnum,
        onNavigate: (Screen) -> Unit,
        LoginScreen: () -> Unit,
        viewModel : DashboardViewModel,
        topBarViewModel: TopBarViewModel,
        isMenuOpen: Boolean,
        onMenuOpenChange: (Boolean) -> Unit,
    ) {
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
                            .fillMaxHeight(),
                        telemetry
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
                            .fillMaxHeight(),
                        telemetry
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
                    LoginScreen,
                    topBarViewModel
                )
                menuBar.MenuBarRenderer(isMenuOpen, onMenuOpenChange, onNavigate)
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
    fun LeftLayout(modifier: Modifier, telemetry: KspTelemetry) {
        val cornerShape = RoundedCornerShape(10.dp)
        val backgroundColor = Color(0xFF1E1C24)
        val borderColor = Color(0xFF90709D)
        val neonColor = Color(0xFFEA00FF)
        Column(
            modifier = modifier.background(Color(0xFF352e43))
        ) {
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var altitudeProgress : Float = (telemetry.altSeaLevel / 70000).toFloat().coerceIn(0f, 1f)
                    VerticalAltimeterRenderer(
                        progress = altitudeProgress,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = 0.dp,
                                horizontal = 0.dp
                            )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                KappiDashboardBox(Modifier.weight(0.5f),neonColor,cornerShape,backgroundColor,borderColor) {
                    Text(
                        text = String.format("%02d", telemetry.currentStage % 100),
                        color = Color.White,
                        fontFamily = PixelifyFontFamily,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center
                        )
                }
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
                    .weight(.2f)
                    .fillMaxSize(),
                telemetry
            )

            MainCenterLayout(
                modifier = Modifier
                    .weight(.8f)
                    .fillMaxSize(),
                telemetry
            )
        }
    }

    @Composable
    fun TopCenterLayout(modifier: Modifier, telemetry: KspTelemetry){
        Row(
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
        ){
            Box(
                Modifier
                    .weight(.225f)
                    .fillMaxSize()
            ){
                val gradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF52ff4a),
                        Color(0xFF24ff62)
                    ),
                    start = Offset(0f,0f),
                    end = Offset.Infinite
                )
                StatusBox(
                    modifier = modifier,
                    state = telemetry.rcs,
                    neonColor = Color(0xFF45FF19),
                    backgroundColor = Color(0xFF45FF19),
                    borderColor = Color(0xFF608d43),
                    displayText = "RCS",
                    brush = gradientBrush
                )

            }
            Box(
                Modifier
                    .weight(.55f)
                    .fillMaxSize()
            ){
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(0.2f)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(
                                        color = if (telemetry.comLink) Color.Green else Color.Red,
                                        shape = CircleShape
                                    )
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF90709D),
                                        shape = CircleShape
                                    )
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val gradientBrush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF8856d3),
                                    Color(0xFF9045ff)
                                ),
                                start = Offset(0f, 0f),
                                end = Offset.Infinite
                            )
                            StatusBox(
                                modifier = Modifier,
                                state = telemetry.lights,
                                neonColor = Color(0xFFb889ff),
                                backgroundColor = Color(0xFF9d5cff),
                                brush = gradientBrush,
                                borderColor = Color(0xFF4d2b80),
                                displayText = "Status 1",
                                displayIcon = ImageVector.vectorResource(id = R.drawable.lightsicon)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val gradientBrush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF5adab4),
                                    Color(0xFF32f7bd)
                                ),
                                start = Offset(0f, 0f),
                                end = Offset.Infinite
                            )
                            StatusBox(
                                modifier = Modifier,
                                state = telemetry.landingGear,
                                neonColor = Color(0xFF74ffd6),
                                backgroundColor = Color(0xFF4fffcb),
                                brush = gradientBrush,
                                borderColor = Color(0xFF208a6b),
                                displayText = "Status 2",
                                displayIcon = ImageVector.vectorResource(id = R.drawable.gearicon)
                            )
                        }


                        Column(
                            modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val gradientBrush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFe19b69),
                                    Color(0xFFff9242)
                                ),
                                start = Offset(0f, 0f),
                                end = Offset.Infinite
                            )
                            StatusBox(
                                modifier = Modifier,
                                state = telemetry.brakes,
                                neonColor = Color(0xFFffb47e),
                                backgroundColor = Color(0xFFffa868),
                                brush = gradientBrush,
                                borderColor = Color(0xFFa6571e),
                                displayText = "Status 3",
                                displayIcon = ImageVector.vectorResource(id = R.drawable.brakesicon)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(0.2f)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(
                                        color = if (telemetry.grounded) Color.Green else Color.Cyan,
                                        shape = CircleShape
                                    )
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF90709D),
                                        shape = CircleShape
                                    )
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                    }
                }
            }
            Box(
                Modifier
                    .weight(.225f)
                    .fillMaxSize()
            ){
                val gradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF95ffff),
                        Color(0xFF6effff)
                    ),
                    start = Offset(0f,0f),
                    end = Offset.Infinite
                )
                StatusBox(
                    modifier = modifier,
                    state = telemetry.sas,
                    neonColor = Color(0xFF98f9ff),
                    backgroundColor = Color(0xFF85d3ff),
                    borderColor = Color(0xFF5199c2),
                    displayText = "SAS",
                    brush = gradientBrush
                )
            }
        }
    }

    @Composable
    fun StatusBox(modifier : Modifier, state : Boolean, neonColor: Color, backgroundColor : Color, brush : Brush, borderColor: Color, displayText : String, displayIcon : ImageVector ? = null){
        val calculatedCornerShape = RoundedCornerShape(10.dp)
        val calculatedBackgroundColor = if(state) backgroundColor else Color(0xFF1E1C24)
        val calculatedBorderColor = if(state) borderColor else Color(0xFF90709D)
        val calculatedNeonColor = if(state) neonColor else Color(0xFFEA00FF)
        val calculatedGradientBrush = if(state) brush else null
        KappiDashboardBox(modifier,calculatedNeonColor,calculatedCornerShape,calculatedBackgroundColor,calculatedBorderColor, calculatedGradientBrush) {
            if(displayIcon == null){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = displayText,
                    color = if(state) Color.White else Color.Gray,
                    fontFamily = PixelifyFontFamily,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }else{
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(23.dp),
                    imageVector = displayIcon,
                    contentDescription = null,
                    tint = if (state) Color.White else Color.Gray,
                )
            }
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
        brush: Brush? = null,
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
                .then(
                    if (brush != null) {
                        Modifier.background(brush)
                    } else {
                        Modifier.background(bg)
                    }
                )
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
            ) {
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
    fun RightLayout(modifier: Modifier, telemetry: KspTelemetry) {
        Column(
            modifier = modifier.background(Color(0xFF352e43))
        ) {
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 0.dp)
            ) {
                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 0.dp)
                        ){
                        VerticalProgressBarRenderer(
                            progress = telemetry.liquidFuel,
                            modifier = Modifier.fillMaxSize(),
                            color = Color(0xFF4c2223),
                            trackColor = Color(0xFFe6510f)
                        )
                }

                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 0.dp)
                ){
                    VerticalProgressBarRenderer(
                        progress = telemetry.oxidizer,
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF25435d),
                        trackColor = Color(0xFF08b0df)
                    )
                }

                Column (
                    modifier = Modifier
                        .weight(.33f)
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp, vertical = 0.dp)
                ){
                    VerticalProgressBarRenderer(
                        progress = telemetry.monoprop,
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF615b62),
                        trackColor = Color(0xFFfffbda)
                    )
                }


            }

            Row(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            ) {
                BatteryIndicator(
                    progress = telemetry.electricCharge,
                    modifier = Modifier.rotate(90f),
                    activeColor = Color.Green
                )
            }
        }

}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
