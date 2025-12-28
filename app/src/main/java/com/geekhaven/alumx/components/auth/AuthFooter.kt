package com.geekhaven.alumx.components.auth

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AuthFooter(
    text: String,
    actionText: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = "$text $actionText",
            textAlign = TextAlign.Center
        )
    }
}