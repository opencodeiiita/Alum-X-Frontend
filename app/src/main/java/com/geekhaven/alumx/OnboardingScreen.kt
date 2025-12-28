package com.geekhaven.alumx


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


val DeepBlueBG = Color(0xFF131924)
val PrimaryBlue = Color(0xFF175BE5)
val AppWhite = Color(0xFFFFFFFF)
val SurfaceColor = Color(0x88212C3D)

private val OnboardingColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = AppWhite,
    background = DeepBlueBG,
    onBackground = AppWhite,
    surface = SurfaceColor,
    onSurface = AppWhite
)


@Composable
fun SlidingAutoCarousel(
    images: List<Int>,
    currentIndex: Int,
    onIndexChange: (Int) -> Unit,
    autoScrollDelay: Long = 2500L
) {
    LaunchedEffect(currentIndex) {
        delay(autoScrollDelay)
        onIndexChange((currentIndex + 1) % images.size)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(1F)
    ) {
        val width = maxWidth

        images.forEachIndexed { index, image ->

            val targetOffset = when {
                index < currentIndex -> -width
                index > currentIndex -> width
                else -> 0.dp
            }

            val offsetX by animateDpAsState(
                targetValue = targetOffset,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                ),
                label = ""
            )

            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .offset(x = offsetX),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    total: Int,
    current: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == current) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == current)
                            PrimaryBlue
                        else
                            Color.LightGray
                    )
            )
        }
    }
}

@Composable
fun OnBoarding(modifier: Modifier = Modifier, onSignUpButtonClicked: () -> Unit,onLogInButtonClicked: () -> Unit) {
    OnboardingTheme {
        OnBoardingContent(modifier,onSignUpButtonClicked,onLogInButtonClicked)
    }
}


@Composable
fun OnboardingTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = OnboardingColorScheme,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}


@Composable
fun OnBoardingContent(modifier: Modifier = Modifier,
                      onNextButtonClicked: () -> Unit,
                      onLogInButtonClicked: () -> Unit
) {
    var carouselIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Image of AlumX",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "AlumX", fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                SlidingAutoCarousel(
                    images = listOf(
                        R.drawable.placeholder1,
                        R.drawable.placeholder2,
                        R.drawable.placeholder3
                    ),
                    currentIndex = carouselIndex,
                    onIndexChange = { carouselIndex = it }
                )


            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DotsIndicator(
                    modifier = Modifier.padding(top = 12.dp),
                    total = 3,
                    current = carouselIndex
                )


                Text(
                    text = buildAnnotatedString {
                        append("Connect. ")
                        withStyle(style = SpanStyle(color = PrimaryBlue)) {
                            append("Mentor. ")
                        }
                        append("Grow.")
                    },
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White.copy(alpha = 0.7f),
                    text = "Join the ultimate alumni network. Access mentorship, blogs, and career tools all in one place."
                )
                Button(
                    onClick = onNextButtonClicked,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        fontSize = 16.sp,
                        text = "Get Started"
                    )

                    Icon(
                        modifier = Modifier.padding(start = 5.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next"
                    )
                }
                OutlinedButton(
                    onClick = onLogInButtonClicked,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        fontSize = 16.sp,
                        text = "Log In",
                        color = AppWhite
                    )

                }

                Row() {
                    Text(
                        text = "By continuing, you accept our ",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Terms of Service",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable(onClick = {}),
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )

                }


            }
        }
    }
}
