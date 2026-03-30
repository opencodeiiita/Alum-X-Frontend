package com.geekhaven.alumx.data.remote.dto

import com.google.gson.annotations.SerializedName

// Request body for creating a post (Matches backend JobPostRequest)
data class CreatePostRequest(
    @SerializedName("username") val username: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrls") val imageUrls: List<String> = emptyList()
)

// Response from GET /api/posts/search (Matches backend PagedPostResponse)
data class PagedPostResponseDto(
    @SerializedName("posts") val posts: List<PostDto>,
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("totalElements") val totalElements: Long,
    @SerializedName("hasNext") val hasNext: Boolean
)

// Single Post Item from Backend (Matches backend JobPostResponse)
data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String, // Backend sends "Username's Job Post"
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String
)