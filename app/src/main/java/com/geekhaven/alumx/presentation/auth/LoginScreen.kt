package com.geekhaven.alumx.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.components.auth.AuthButton
import com.geekhaven.alumx.components.auth.AuthTextField
import com.geekhaven.alumx.components.auth.SocialButton

// Colors
val BackgroundDark = Color(0xFF0F1116)
val PrimaryBlue = Color(0xFF2563EB)
val TextGrey = Color(0xFF9CA3AF)

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
            .background(BackgroundDark)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(PrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
             Box(modifier = Modifier.size(32.dp).background(Color.White, RoundedCornerShape(4.dp)))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "AlumX",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp
        )
        Text(
            text = "Connect. Mentor. Grow.",
            color = TextGrey,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            placeholder = "you@example.com",
            leadingIcon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "••••••••",
            leadingIcon = Icons.Default.Lock
        )
        
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "Forgot Password?",
                color = PrimaryBlue,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            text = "Log In",
            onClick = onLoginClick,
            isPrimary = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f).height(1.dp).background(Color(0xFF374151)))
            Text(
                text = "OR CONTINUE WITH",
                color = Color(0xFF6B7280),
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
             Box(Modifier.weight(1f).height(1.dp).background(Color(0xFF374151)))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
             SocialButton(
                 text = "Google",
                 icon = Icons.Default.Person,
                 iconTint = Color(0xFFEA4335),
                 onClick = {},
                 modifier = Modifier.weight(1f)
             )
             SocialButton(
                 text = "LinkedIn",
                 icon = Icons.Default.Person, // Placeholder
                 iconTint = Color(0xFF0A66C2),
                 onClick = {},
                 modifier = Modifier.weight(1f)
             )
        }

        Spacer(modifier = Modifier.weight(1f))

        // --- Footer ---
        Row(
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text = "Don't have an account? ",
                color = TextGrey,
                fontSize = 14.sp
            )
            Text(
                text = "Sign Up",
                color = PrimaryBlue,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigateToRegister() }
            )
        }
    }
}
