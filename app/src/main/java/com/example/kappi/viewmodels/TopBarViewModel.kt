package com.example.kappi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kappi.data.TelemetryRepo

class TopBarViewModel : ViewModel() {
    public val isConnected = TelemetryRepo.isConnected
}