package com.example.kappi.classes

import androidx.compose.runtime.Composable
import com.example.kappi.R
import com.example.kappi.models.HomeDataDetail

class HomeDetailLoader {
    @Composable
    public fun LoadHomeDetails(detailSreen: () -> Unit) : List<HomeDataDetail> {
        return listOf<HomeDataDetail>(
            HomeDataDetail(R.string.HomeInfo5,R.string.HomeInfo6, null),
            HomeDataDetail(R.string.HomeInfo3,R.string.HomeInfo4,null),
            HomeDataDetail(R.string.HomeInfo7,R.string.HomeInfo8,null),
            HomeDataDetail(R.string.HomeInfo9,R.string.HomeInfo10,null)
        )
    }
}
