package com.geekhaven.alumx.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Core token colors - keeping consistent with theme
val BackgroundDark = Color(0xFF0F1116)
val PrimaryBlue = Color(0xFF2563EB)
val TextGrey = Color(0xFF9CA3AF)
val BorderGrey = Color(0xFF374151)

data class OnboardingPageData(
    val title: AnnotatedString,
    val description: String,
    val imageColor: Color
)

@Composable
fun OnboardingScreen(onGetStarted: () -> Unit) {
    val pages = listOf(
        OnboardingPageData(
            title = buildAnnotatedString {
                append("Connect. ")
                withStyle(style = SpanStyle(color = PrimaryBlue)) { append("Mentor.") }
                append(" Grow.")
            },
            description = "Join the ultimate alumni network. Access mentorship, blogs, and career tools all in one place.",
            imageColor = Color(0xFFE0E0E0)
        ),
        OnboardingPageData(
            title = buildAnnotatedString {
                append("Network with ")
                withStyle(style = SpanStyle(color = PrimaryBlue)) { append("Alumni.") }
            },
            description = "Find mentors from your dream companies and get guidance on your career path.",
            imageColor = Color(0xFFD1D5DB)
        ),
        OnboardingPageData(
            title = buildAnnotatedString {
                append("Unlock ")
                withStyle(style = SpanStyle(color = PrimaryBlue)) { append("Careers.") }
            },
            description = "Get exclusive job referrals and stay updated with the latest industry trends.",
            imageColor = Color(0xFF9CA3AF)
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    androidx.compose.runtime.LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Header (Logo) ---
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PrimaryBlue),
                contentAlignment = Alignment.Center
            ) {
                 Box(modifier = Modifier.size(16.dp).background(Color.White, RoundedCornerShape(2.dp)))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AlumX",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))

        Spacer(modifier = Modifier.height(24.dp))

        // --- Pager Indicators ---
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pages.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width = if (isSelected) 32.dp else 6.dp
                val color = if (isSelected) PrimaryBlue else BorderGrey

                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .width(width)
                        .clip(RoundedCornerShape(3.dp))
                        .background(color)
                )
                if (index < pages.size - 1) Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- Pager Content ---
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
             val pageData = pages[pageIndex]
             
             Column(
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Top,
                 modifier = Modifier.fillMaxSize()
             ) {
                // Image Container (Dynamic)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(pageData.imageColor) 
                ) {
                     Box(
                         modifier = Modifier
                             .fillMaxSize()
                             .background(Color.Black.copy(alpha=0.1f))
                     )
                }

                Spacer(modifier = Modifier.height(24.dp))
                 Text(
                     text = pageData.title,
                     color = Color.White,
                     fontSize = 28.sp,
                     fontWeight = FontWeight.Bold,
                     textAlign = TextAlign.Center,
                     lineHeight = 34.sp
                 )
                 
                 Spacer(modifier = Modifier.height(12.dp))
                 
                 Text(
                     text = pageData.description,
                     color = TextGrey,
                     fontSize = 15.sp,
                     textAlign = TextAlign.Center,
                     lineHeight = 22.sp
                 )
             }
        }

        // --- Bottom Buttons ---
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(1.dp, BorderGrey, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                elevation = null
            ) {
                Text(
                    text = "Log In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
             Spacer(modifier = Modifier.height(16.dp))
             
             Text(
                 text = "By continuing, you accept our Terms of Service",
                 color = Color(0xFF6B7280),
                 fontSize = 12.sp,
                 textAlign = TextAlign.Center
             )
        }
    }
}
