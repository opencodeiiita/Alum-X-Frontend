package com.geekhaven.alumx.data.repository

import android.util.Log // Import Log
import com.geekhaven.alumx.R
import com.geekhaven.alumx.data.remote.api.PostApiService
import com.geekhaven.alumx.data.remote.dto.CreatePostRequest
import com.geekhaven.alumx.model.Post
import com.geekhaven.alumx.model.PostCategory
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PostRepository"

@Singleton
class PostRepository @Inject constructor(
    private val postApi: PostApiService
) {
    // TODO: Ideally inject a TokenManager (DataStore)
    private var cachedToken: String = ""
    private var currentUsername: String = "User" // Default if not set

    fun setAuthDetails(token: String, username: String) {
        cachedToken = "Bearer $token"
        currentUsername = username
        Log.d(TAG, "Auth details set for user: $username")
    }

    suspend fun createPost(description: String): Result<Unit> {
        return try {
            Log.d(TAG, "API Call: Creating post for user: $currentUsername")

            if (cachedToken.isBlank()) {
                Log.e(TAG, "Error: Token is missing! Did you call setAuthDetails?")
                return Result.failure(Exception("Not Authenticated"))
            }

            val request = CreatePostRequest(
                username = currentUsername,
                description = description,
                imageUrls = emptyList()
            )

            val response = postApi.createPost(cachedToken, request)
            Log.d(TAG, "API Response Code: ${response.code()}")

            if (response.isSuccessful) {
                Log.d(TAG, "Post created successfully")
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e(TAG, "Failed to create post. Code: ${response.code()}, Error: $errorBody")
                Result.failure(Exception("Failed to create post: ${response.code()} $errorBody"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during createPost", e)
            Result.failure(e)
        }
    }

    suspend fun getPosts(): Result<List<Post>> {
        return try {
            if (cachedToken.isBlank()) {
                Log.e(TAG, "getPosts Error: Token is missing!")
            }

            val response = postApi.getPosts(cachedToken)
            if (response.isSuccessful && response.body() != null) {
                val backendPosts = response.body()!!.posts
                Log.d(TAG, "Fetched ${backendPosts.size} posts from API")

                val uiPosts = backendPosts.map { dto ->
                    val author = dto.title.replace("'s Job Post", "")
                    Post(
                        authorName = author,
                        authorDescription = "Alumni",
                        postText = dto.content,
                        fullContent = dto.content,
                        likes = 0,
                        comments = 0,
                        reposts = 0,
                        placeName = "Campus",
                        imageRes = R.drawable.placeholder1,
                        postCategory = PostCategory.ANNOUNCEMENTS,
                        title = dto.title
                    )
                }
                Result.success(uiPosts)
            } else {
                Log.e(TAG, "getPosts failed. Code: ${response.code()}")
                Result.failure(Exception("Error fetching posts"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception in getPosts", e)
            Result.failure(e)
        }
    }
}