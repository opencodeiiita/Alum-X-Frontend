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
import com.geekhaven.alumx.presentation.post.PostDetailScreen
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
    CreatePost,
    PostDetail
}


@Composable
fun AlumXApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AlumXScreen.Home.name,
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
        composable(route = AlumXScreen.PostDetail.name) {
            PostDetailScreen(
                post = com.geekhaven.alumx.model.Post(
                    authorName = "Sarah Jenkins",
                    authorDescription = "Senior Product Manager at TechCorp",
                    postText = "Making a career change is never easy...",
                    likes = 124,
                    comments = 45,
                    reposts = 12,
                    placeName = "Career",
                    imageRes = R.drawable.placeholder2,
                    postCategory = com.geekhaven.alumx.model.PostCategory.ANNOUNCEMENTS,
                    title = "Navigating the Career Pivot: From Engineering to Product Management",
                    tags = listOf("CAREER", "TECH", "ALUMNI STORIES"),
                    fullContent = "Making a career change is never easy, especially five years post-graduation. When I first graduated with my Mechanical Engineering degree, I thought I had my entire trajectory mapped out. I'd work in automotive, maybe move to aerospace, and eventually lead a technical team.\n\nThe Pivot Point\n\nThe transition wasn't overnight. It started with side projects and coffee chats with alumni. I learned that Product Management sits at the intersection of technology, business, and user experience—a perfect blend for my curiosity.\n\n\"The most valuable skill you can bring from engineering to product is the ability to break down complex problems into manageable chunks.\"\n\nFor anyone looking to make a similar jump, my advice is simple: start acting like a PM in your current role. Take ownership of the \"why\", communicate clearly with stakeholders, and never stop learning about your users."
                ),
                onBackClick = {
                    navController.popBackStack()
                }
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