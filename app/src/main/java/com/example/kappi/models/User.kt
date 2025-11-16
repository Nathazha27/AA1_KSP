package com.example.kappi.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class User(
    @DrawableRes var UserImage: Int,
    var Username: MutableState<String>,
    var Password: MutableState<String>,
    var Token: MutableState<String>
)
