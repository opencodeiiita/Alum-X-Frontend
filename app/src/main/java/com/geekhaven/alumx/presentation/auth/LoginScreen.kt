package com.geekhaven.alumx.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

import com.geekhaven.alumx.components.auth.AuthHeader
import com.geekhaven.alumx.components.auth.AuthTextField
import com.geekhaven.alumx.components.auth.AuthButton
import com.geekhaven.alumx.components.auth.AuthFooter

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AuthHeader(
            title = "Welcome Back 👋",
            subtitle = "Login to continue"
        )

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthButton(
            text = "Login",
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthFooter(
            text = "Don't have an account?",
            actionText = "Register",
            onClick = onNavigateToRegister
        )
    }
}