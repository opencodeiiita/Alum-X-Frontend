package com.geekhaven.alumx.data.remote

data class JobPostRequestDto(
    val username: String,
    val description: String,
    val imageUrls: List<String> = emptyList()
)
