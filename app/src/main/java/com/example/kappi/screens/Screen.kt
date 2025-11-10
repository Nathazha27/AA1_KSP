package com.example.kappi.screens

import androidx.annotation.StringRes

sealed class Screen(
    @StringRes val screenName: Int
)
