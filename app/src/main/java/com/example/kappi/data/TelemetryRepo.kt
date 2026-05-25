package com.example.kappi.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.proto.KspTelemetry

object TelemetryRepo {
    private val client = HttpClient(OkHttp) { install(WebSockets) }

    private var _ip = "192.168.0.0"
    private var _port = 27415

    private val _telemetry = MutableStateFlow(KspTelemetry.getDefaultInstance())
    val telemetry = _telemetry.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    private var connectionJob: Job? = null

    fun updateAddress(ip: String, port: Int) {
        _ip = ip
        _port = port
        startConnection()
    }

    fun startConnection() {
        connectionJob?.cancel()
        connectionJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    client.webSocket(
                        method = HttpMethod.Get,
                        host = _ip,
                        port = _port,
                        path = "/ksp"
                    ) {
                        _isConnected.value = true
                        for (frame in incoming) {
                            if (frame is Frame.Binary) {
                                _telemetry.value = KspTelemetry.parseFrom(frame.readBytes())
                            }
                        }
                    }
                } catch (e: Exception) {
                    _isConnected.value = false
                }
                delay(5000)
            }
        }
    }

    public fun stopConnection(){
        fun stopConnection() {
            connectionJob?.cancel()
            _isConnected.value = false
        }
    }
}