package com.example.postcard.ui.theme.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.postcard.ui.theme.screens.onboarding.OnboardingScreen
import com.example.postcard.ui.theme.screens.onboarding.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.ONBOARDING
    ) {
        composable(AppDestination.ONBOARDING) {
            OnboardingScreen(
                onContinue = {
                    navController.navigate(AppDestination.HOME) {
                        popUpTo(AppDestination.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestination.HOME) {
            HomeScreen()
        }
    }
}


