package com.geekhaven.alumx.data.remote.api

import com.geekhaven.alumx.data.remote.dto.CreatePostRequest
import com.geekhaven.alumx.data.remote.dto.PagedPostResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PostApiService {

    // CHANGED: Removed "api/" prefix. Now it is just "posts/search"
    @GET("posts/search")
    suspend fun getPosts(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("keyword") keyword: String? = null
    ): Response<PagedPostResponseDto>

    // CHANGED: Removed "api/" prefix. Now it is just "posts"
    // This combined with your BaseURL (.../api/) will result in .../api/posts
    @POST("posts")
    suspend fun createPost(
        @Header("Authorization") token: String,
        @Body request: CreatePostRequest
    ): Response<Any>
}