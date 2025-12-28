package com.geekhaven.alumx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekhaven.alumx.presentation.auth.LoginScreen
import com.geekhaven.alumx.presentation.auth.RegisterScreen
import com.geekhaven.alumx.presentation.onboarding.OnboardingScreen
import com.geekhaven.alumx.presentation.onboarding.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    // TODO: navigate to home later
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.popBackStack()
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}