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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

class UserPic {
    @Composable
    public fun UserPicRenderer(user: User, LoginScreen: () -> Unit){
        Button(
            onClick = LoginScreen,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            modifier = Modifier.padding(2.dp),
        ) {
            Image(
                painter = painterResource(user.UserImage),
                contentDescription = null,
                modifier = Modifier.size(50.dp).clip(CircleShape)
            )
        }
    }
}