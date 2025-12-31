package com.geekhaven.alumx.presentation.home

import com.geekhaven.alumx.model.Post

data class HomeUiState(
    val selectedBottomIndex: Int = 0,
    val searchQuery: String = "",
    val posts: List<Post> = emptyList()
)
