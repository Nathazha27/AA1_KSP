package com.example.kappi.models

import androidx.annotation.DrawableRes

data class StateImg(
    @DrawableRes var on_onState: Int,
    @DrawableRes var off_onState: Int,
    @DrawableRes var on_connectingState: Int,
    @DrawableRes var off_connectingState: Int,
    @DrawableRes var on_offState: Int,
    @DrawableRes var off_offState: Int
)
