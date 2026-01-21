package com.geekhaven.alumx.data.remote.dto

data class RegisterRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val role: String
)

data class LoginRequest(
    val emailOrUsername: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val tokenExpiryTime: Long,
    val user: UserBasicInfo
)

data class UserBasicInfo(
    val id: Long,
    val username: String,
    val email: String,
    val name: String,
    val role: String
)

data class RegisterResponse(
    val userId: Long,
    val username: String,
    val email: String,
    val role: String,
    val message: String?
)