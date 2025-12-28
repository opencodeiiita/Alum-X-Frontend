package com.geekhaven.alumx.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AlumXColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = AppWhite,
    background = DeepBlueBG,
    onBackground = AppWhite,
    surface = SurfaceColor,
    onSurface = AppWhite
)


@Composable
fun AlumXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = AlumXColorScheme,
        typography = Typography,
        content = content
    )
}