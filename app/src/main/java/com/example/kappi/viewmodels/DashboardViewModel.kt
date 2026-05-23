package com.example.kappi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.proto.KspTelemetry

class DashboardViewModel : ViewModel() {

    private val _telemetry = MutableStateFlow(KspTelemetry.getDefaultInstance())
    val telemetry = _telemetry.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private val client = HttpClient(Android) {
        install(WebSockets)
    }

    init {
        startConnection()
    }

    private fun startConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                client.webSocket(
                    method = HttpMethod.Get,
                    host = "10.0.2.2",
                    port = 8080,
                    path = "/ksp"
                ) {
                    _isConnected.value = true
                    println("¡Conectado al Bridge con éxito!")

                    for (frame in incoming) {
                        if (frame is Frame.Binary) {
                            val bytes = frame.readBytes()
                            val data = KspTelemetry.parseFrom(bytes)

                            _telemetry.value = data
                        }
                    }

                    _isConnected.value = false
                }
            } catch (e: Exception) {
                _isConnected.value = false
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close()
    }
}