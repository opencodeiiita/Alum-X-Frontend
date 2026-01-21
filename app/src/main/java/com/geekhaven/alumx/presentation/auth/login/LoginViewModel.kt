package com.geekhaven.alumx.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.alumx.data.repository.AuthRepository // Import your Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel // Use Hilt for easy injection
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Add a state for the Request status (Loading/Success/Error)
    private val _loginRequestState = MutableStateFlow<LoginRequestState>(LoginRequestState.Idle)
    val loginRequestState = _loginRequestState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun performLogin() {
        val currentState = _uiState.value
        _loginRequestState.value = LoginRequestState.Loading

        viewModelScope.launch {
            // Backend expects "emailOrUsername"
            val result = repository.loginUser(currentState.email, currentState.password)

            result.onSuccess {
                _loginRequestState.value = LoginRequestState.Success
            }.onFailure { error ->
                _loginRequestState.value = LoginRequestState.Error(error.message ?: "Login Failed")
            }
        }
    }

    // Reset state after navigation to prevent loops
    fun resetState() {
        _loginRequestState.value = LoginRequestState.Idle
    }
}

// Simple sealed class for Request Status
sealed class LoginRequestState {
    object Idle : LoginRequestState()
    object Loading : LoginRequestState()
    object Success : LoginRequestState()
    data class Error(val message: String) : LoginRequestState()
}