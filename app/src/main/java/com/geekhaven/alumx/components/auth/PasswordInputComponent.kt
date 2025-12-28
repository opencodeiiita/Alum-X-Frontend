package com.geekhaven.alumx.components.auth

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.ui.theme.AlumXTheme
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.ui.theme.SurfaceColor


@Composable
fun PasswordInputComponent(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: ImageVector,
    input: String,
    changeFunc: (String) -> Unit,
    placeholderText: String
) {
    Column() {
        Text(
            modifier= Modifier.padding(start = 5.dp),

            fontSize = 14.sp, lineHeight = 20.sp, color = Color.White, text = title
        )
        var isPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = input,
            onValueChange = changeFunc,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceColor,
                unfocusedContainerColor = SurfaceColor,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.1f)
            ),
            placeholder = { Text(placeholderText) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon, contentDescription = null
                )
            },
            trailingIcon = {
                val image = if (isPasswordVisible) Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                val description = if (isPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            })
    }
}


