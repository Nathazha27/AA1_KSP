package com.example.kappi.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class State(
    @DrawableRes var onState: Int,
    @DrawableRes var connectingState: Int,
    @DrawableRes var offState: Int
    )
