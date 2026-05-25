package com.example.kappi.models

import androidx.annotation.StringRes

data class HomeDataDetail(
    @StringRes val title: Int,
    @StringRes val info: Int,
    val onClick : (() -> Unit)? = null
)
