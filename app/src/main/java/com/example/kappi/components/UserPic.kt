package com.example.kappi.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kappi.models.User
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

class UserPic {
    @Composable
    public fun UserPicRenderer(user: User){
        Button(
            onClick = {
            //Change to LoginScreen
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
            Image(
                painter = painterResource(user.UserImage),
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(CircleShape)
            )
        }
    }
}