package com.geekhaven.alumx.presentation.auth.register

data class RegisterUiState(
    val email: String = "",
    val username: String = "",
    val branch: String = "",
    val year: String = "",
    val password: String = "",
    val userType: UserType = UserType.STUDENT
)
