package com.geekhaven.alumx.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.R

@Composable
fun AuthHeader(
    title: String,
    subtitle: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = title, fontSize = 26.sp)
        Text(text = subtitle, fontSize = 14.sp)
    }
}