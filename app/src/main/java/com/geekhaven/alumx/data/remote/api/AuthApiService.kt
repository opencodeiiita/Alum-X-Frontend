package com.geekhaven.alumx.data.remote.api

import com.geekhaven.alumx.data.remote.dto.LoginRequest
import com.geekhaven.alumx.data.remote.dto.LoginResponse
import com.geekhaven.alumx.data.remote.dto.RegisterRequest
import com.geekhaven.alumx.data.remote.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    // Matches @PostMapping("/register")
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    // Matches @PostMapping("/login")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}