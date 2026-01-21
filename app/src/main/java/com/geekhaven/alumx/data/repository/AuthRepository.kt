package com.geekhaven.alumx.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.geekhaven.alumx.data.remote.api.AuthApiService
import com.geekhaven.alumx.data.remote.dto.LoginRequest
import com.geekhaven.alumx.data.remote.dto.LoginResponse
import com.geekhaven.alumx.data.remote.dto.RegisterRequest
import com.geekhaven.alumx.data.remote.dto.RegisterResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

class AuthRepository @Inject constructor(
    private val api: AuthApiService,
    @ApplicationContext private val context: Context,
) {

    // 1. Login Function
    suspend fun loginUser(emailOrUser: String, pass: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(emailOrUsername = emailOrUser, password = pass)
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                saveToken(body.accessToken) // Save JWT immediately
                Result.success(body)
            } else {
                Result.failure(Exception("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 2. Register Function
    suspend fun registerUser(
        username: String, name: String, email: String, pass: String, role: String
    ): Result<RegisterResponse> {
        return try {
            val request = RegisterRequest(username, name, email, pass, role)
            val response = api.register(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Helper to save token to DataStore
    private suspend fun saveToken(token: String) {
        val key = stringPreferencesKey("jwt_token")

        // Now 'context.dataStore' will work because of the extension defined above
        context.dataStore.edit { prefs ->
            prefs[key] = token
        }
    }
}