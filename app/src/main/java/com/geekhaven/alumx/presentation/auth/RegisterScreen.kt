package com.geekhaven.alumx.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.components.auth.*

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
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
            title = "Create Account 🚀",
            subtitle = "Join the AlumX community"
        )

        AuthTextField(name, { name = it }, "Full Name")
        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(email, { email = it }, "Email")
        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            password,
            { password = it },
            "Password",
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        AuthButton(
            text = "Register",
            modifier = Modifier.fillMaxWidth(),
            onClick = onRegisterClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthFooter(
            text = "Already have an account?",
            actionText = "Login",
            onClick = onNavigateToLogin
        )
    }
}