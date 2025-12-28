package com.geekhaven.alumx.components.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.SurfaceColor

@Composable
fun AltButton(modifier: Modifier= Modifier, buttonText:String, buttonIcon: Int){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = SurfaceColor,        // background
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(52.dp)
    ) {

        Image(
            painter = painterResource(buttonIcon),
            contentDescription = null
        )
        Text(
            fontSize = 16.sp,
            text = buttonText
        )
    }
}