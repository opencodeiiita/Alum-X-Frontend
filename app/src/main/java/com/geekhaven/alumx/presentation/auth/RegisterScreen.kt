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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.components.auth.AuthButton
import com.geekhaven.alumx.components.auth.AuthTextField
import com.geekhaven.alumx.components.auth.SocialButton

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isStudent by remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(24.dp)
            .verticalScroll(scrollState),
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
            text = "Create Account",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Join the AlumX community today.",
            color = TextGrey,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name",
            placeholder = "John Doe",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            placeholder = "you@example.com",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "••••••••",
            leadingIcon = Icons.Default.Lock
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "I am a",
                color = Color(0xFFD1D5DB),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F2937), RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                 ToggleOption(
                     text = "Student",
                     isSelected = isStudent,
                     onClick = { isStudent = true },
                     modifier = Modifier.weight(1f)
                 )
                 ToggleOption(
                     text = "Alumni",
                     isSelected = !isStudent,
                     onClick = { isStudent = false },
                     modifier = Modifier.weight(1f)
                 )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
             AuthTextField(
                 value = "2024",
                 onValueChange = {},
                 label = "Grad. Year",
                 placeholder = "",
                 leadingIcon = Icons.Default.Home,
                 modifier = Modifier.weight(1f)
             )
             AuthTextField(
                 value = "CSE",
                 onValueChange = {},
                 label = "Branch",
                 placeholder = "",
                 leadingIcon = Icons.Default.Info,
                 modifier = Modifier.weight(1f)
             )
        }

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            text = "Sign Up",
            onClick = onRegisterClick,
            isPrimary = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f).height(1.dp).background(Color(0xFF374151)))
            Text(
                text = "OR SIGN UP WITH",
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
                 icon = Icons.Default.Person,
                 iconTint = Color(0xFF0A66C2),
                 onClick = {},
                 modifier = Modifier.weight(1f)
             )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Footer ---
        Row(
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text = "Already have an account? ",
                color = TextGrey,
                fontSize = 14.sp
            )
            Text(
                text = "Log In",
                color = PrimaryBlue,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }
    }
}

@Composable
fun ToggleOption(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color(0xFF374151) else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else TextGrey,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}
