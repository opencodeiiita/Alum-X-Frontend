package com.geekhaven.alumx.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AlumXApi {
    @POST("api/job-posts")
    suspend fun createJobPost(@Body request: JobPostRequestDto): Response<Any>
}
