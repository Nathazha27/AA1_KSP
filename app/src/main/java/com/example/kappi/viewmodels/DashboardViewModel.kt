package com.example.kappi.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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



    private val _telemetry = MutableStateFlow(KspTelemetry.getDefaultInstance())
    val telemetry = _telemetry.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    init {
        startConnection()
    }

    private fun startConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            while(isActive){
                try {
                    Log.d("KSP_WS", "Intentando conectar al Bridge...")
                    client.webSocket(
                        method = HttpMethod.Get,
                        host = "10.0.2.2",
                        port = 8080,
                        path = "/ksp"
                    ) {
                        _isConnected.value = true
                        Log.d("KSP_WS", "¡Conectado!")

                        for (frame in incoming) {
                            if (frame is Frame.Binary) {
                                val bytes = frame.readBytes()
                                val data = KspTelemetry.parseFrom(bytes)

                                _telemetry.value = data
                            }
                        }

                        _isConnected.value = false
                        Log.d("KSP_WS", "Conexión cerrada por el servidor.")
                    }
                } catch (e: Exception) {
                    _isConnected.value = false
                    Log.e("KSP_WS", "Fallo de conexión: ${e.message}")
                }
                Log.d("KSP_WS", "Reintentando en 5 segundos...")
                delay(5000)

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close()
    }
}