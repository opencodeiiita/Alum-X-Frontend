package com.geekhaven.alumx.presentation.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geekhaven.alumx.components.auth.AltButton
import com.geekhaven.alumx.components.auth.HeaderComponent
import com.geekhaven.alumx.components.auth.PasswordInputComponent
import com.geekhaven.alumx.components.auth.SubmitButton
import com.geekhaven.alumx.components.auth.TextInputComponent
import com.geekhaven.alumx.ui.theme.PrimaryBlue
import com.geekhaven.alumx.R
import com.geekhaven.alumx.components.auth.UserTypeToggle
import com.geekhaven.alumx.components.auth.YearInputComponent

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onLogInButtonClicked: () -> Unit,
    onRegisterSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(Modifier.height(16.dp))
                HeaderComponent(
                    Modifier,
                    headerText = "Create Account",
                    headerSubText = "Join the AlumX community today."
                )
                Spacer(Modifier.height(16.dp))
                TextInputComponent(
                    Modifier.fillMaxWidth(),
                    "Username",
                    Icons.Default.Person,
                    uiState.username,
                    viewModel::onUsernameChange,
                    "John Doe"
                )
                TextInputComponent(
                    Modifier.fillMaxWidth(),
                    "Email Address",
                    Icons.Default.Email,
                    uiState.email,
                    viewModel::onEmailChange,
                    "example@gmail.com"
                )
                PasswordInputComponent(
                    Modifier,
                    "Password",
                    Icons.Default.Lock,
                    uiState.password,
                    viewModel::onPasswordChange,
                    "*********"
                )

                UserTypeToggle(
                    selected = uiState.userType,
                    onSelect = viewModel::onUserTypeChange
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    YearInputComponent(
                        Modifier.weight(1f),
                        "Grad. Year",
                        Icons.Default.CalendarToday,
                        uiState.year,
                        viewModel::onYearChange,
                        "2025"
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    TextInputComponent(
                        Modifier.weight(1f),
                        "Branch",
                        Icons.Default.School,
                        uiState.branch,
                        viewModel::onBranchChange,
                        "CSE"
                    )
                }

                SubmitButton("Sign Up", Icons.AutoMirrored.Filled.ArrowForward, onRegisterSuccess)

                Text(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White.copy(alpha = 0.7f),
                    text = "OR SIGN UP WITH"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AltButton(
                        modifier = Modifier.weight(1f),
                        buttonText = "Google",
                        buttonIcon = R.drawable.ic_launcher_foreground
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    AltButton(
                        modifier = Modifier.weight(1f),
                        buttonText = "Linkedin",
                        buttonIcon = R.drawable.ic_launcher_foreground
                    )
                }

                Row {
                    Text(
                        text = "Already have an account? ",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Log in",
                        modifier = Modifier.clickable(onClick = onLogInButtonClicked),
                        fontSize = 12.sp,
                        color = PrimaryBlue,
                        textAlign = TextAlign.Center
                    )

                }

            }
        }
    }
}