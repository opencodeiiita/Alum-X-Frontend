package com.example.postcard.ui.theme.screens.onboarding.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.postcard.ui.theme.components.post.PostItem

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
        .background(Color(0xFF0E1320)),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(5) {
            PostItem(
                authorName = "User",
                authorSubtitle = "Desingnation / Profession",
                time = "2h • Edited",
                postText = "Just published How the day went",
                likes = 2046,
                comments = 124,
                reposts = 16,
                showImage = true
            )
        }
    }
}
