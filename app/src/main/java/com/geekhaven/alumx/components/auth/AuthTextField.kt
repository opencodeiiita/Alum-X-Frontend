package com.geekhaven.alumx.components.auth

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = {
            if (placeholder.isNotEmpty()) Text(placeholder)
        },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null)
            }
        },
        modifier = modifier,
        singleLine = true,
        visualTransformation = visualTransformation
    )
}
