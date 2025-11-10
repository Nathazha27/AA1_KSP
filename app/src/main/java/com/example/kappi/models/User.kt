package com.example.kappi.models

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes var UserImage: Int,
    var Name: String,
    var Token: String
)
