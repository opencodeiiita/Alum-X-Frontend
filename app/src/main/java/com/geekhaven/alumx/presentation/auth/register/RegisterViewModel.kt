package com.geekhaven.alumx.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.alumx.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _registerRequestState = MutableStateFlow<RegisterRequestState>(RegisterRequestState.Idle)
    val registerRequestState = _registerRequestState.asStateFlow()

    // ... Keep your existing onChanges (email, username, branch, etc) ...
    fun onEmailChange(email: String) { _uiState.update { it.copy(email = email) } }
    fun onUsernameChange(username: String) { _uiState.update { it.copy(username = username) } }
    fun onBranchChange(branch: String) { _uiState.update { it.copy(branch = branch) } }
    fun onYearChange(year: String) { _uiState.update { it.copy(year = year) } }
    fun onPasswordChange(password: String) { _uiState.update { it.copy(password = password) } }
    fun onUserTypeChange(type: UserType) { _uiState.update { it.copy(userType = type) } }

    fun performRegister() {
        _registerRequestState.value = RegisterRequestState.Loading
        val state = _uiState.value

        viewModelScope.launch {
            // NOTE: Backend requires 'name' (Full Name). Your UI only has 'username'.
            // For now, we are sending 'username' as the 'name' too.
            val result = repository.registerUser(
                username = state.username,
                name = state.username,
                email = state.email,
                pass = state.password,
                role = state.userType.name
            )

            result.onSuccess {
                _registerRequestState.value = RegisterRequestState.Success
            }.onFailure { error ->
                _registerRequestState.value = RegisterRequestState.Error(error.message ?: "Registration Failed")
            }
        }
    }

    fun resetState() {
        _registerRequestState.value = RegisterRequestState.Idle
    }
}

sealed class RegisterRequestState {
    object Idle : RegisterRequestState()
    object Loading : RegisterRequestState()
    object Success : RegisterRequestState()
    data class Error(val message: String) : RegisterRequestState()
}