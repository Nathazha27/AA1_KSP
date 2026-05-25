package com.example.kappi.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kappi.data.TelemetryRepo
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.proto.KspTelemetry


class DashboardViewModel : ViewModel() {
    val telemetry = TelemetryRepo.telemetry
    val isConnected = TelemetryRepo.isConnected

    fun updateConfig(ip: String, port: Int) {
        TelemetryRepo.updateAddress(ip, port)
    }
}