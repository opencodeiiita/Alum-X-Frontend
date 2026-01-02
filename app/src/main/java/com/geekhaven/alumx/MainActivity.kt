package com.geekhaven.alumx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geekhaven.alumx.presentation.auth.login.LoginScreen
import com.geekhaven.alumx.presentation.auth.register.RegisterScreen
import com.geekhaven.alumx.presentation.createPost.CreatePostScreen
import com.geekhaven.alumx.presentation.home.HomeScreen
import com.geekhaven.alumx.presentation.onboarding.OnBoarding
import com.geekhaven.alumx.ui.theme.AlumXTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlumXTheme {
                AlumXApp()
            }
        }
    }
}

enum class AlumXScreen() {
    OnBoarding,
    Login,
    Register,
    Home,
    CreatePost
}


@Composable
fun AlumXApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AlumXScreen.OnBoarding.name,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = AlumXScreen.OnBoarding.name) {
            OnBoarding(
                modifier = Modifier.fillMaxSize(),
                onSignUpButtonClicked = {
                    navController.navigate(AlumXScreen.Register.name)
                },
                onLogInButtonClicked = {
                    navController.navigate(AlumXScreen.Login.name)
                }
            )
        }
        composable(route = AlumXScreen.Login.name) {
            LoginScreen(
                onSignUpButtonClicked = {
                    navController.navigate(AlumXScreen.Register.name)
                },
                onLoginSuccess = {
                    navController.navigate(AlumXScreen.Home.name) {
                        popUpTo(AlumXScreen.OnBoarding.name) { inclusive = true }
                    }
                },
            )
        }
        composable(route = AlumXScreen.Register.name) {
            RegisterScreen(
                onLogInButtonClicked = {
                    navController.navigate(AlumXScreen.Login.name)
                },
                onRegisterSuccess = {
                    navController.navigate(AlumXScreen.Home.name) {
                        popUpTo(AlumXScreen.OnBoarding.name) { inclusive = true }
                    }
                }
            )
        }
        composable(route = AlumXScreen.Home.name) {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = AlumXScreen.CreatePost.name) {
            CreatePostScreen(
                navController = navController
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlumXAppPreview() {
    AlumXTheme {
        AlumXApp()
    }
}