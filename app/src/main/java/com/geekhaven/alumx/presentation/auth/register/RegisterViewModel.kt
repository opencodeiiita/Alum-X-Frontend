package com.geekhaven.alumx.presentation.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }
    fun onBranchChange(branch: String) {
        _uiState.update { it.copy(branch = branch) }
    }

    fun onYearChange(year: String) {
        _uiState.update { it.copy(year = year) }
    }
    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onUserTypeChange(type: UserType) {
        _uiState.update { it.copy(userType = type) }
    }


}