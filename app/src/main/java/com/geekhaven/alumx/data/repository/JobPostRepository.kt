package com.geekhaven.alumx.data.repository

import com.geekhaven.alumx.data.remote.JobPostRequestDto
import com.geekhaven.alumx.data.remote.RetrofitInstance

class JobPostRepository {
    private val api = RetrofitInstance.api

    suspend fun createJobPost(username: String, description: String, imageUrls: List<String>): Result<Unit> {
        return try {
            val request = JobPostRequestDto(
                username = username,
                description = description,
                imageUrls = imageUrls
            )
            val response = api.createJobPost(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create post: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
