package com.example.pr01.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.AuthRequest
import com.example.pr01.data.Profile
import com.example.pr01.service.RetrofitClient
import com.example.pr01.service.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val tokenManager: TokenManager
) : ViewModel() {

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean> = _isAuthorized

    var userProfile by mutableStateOf<Profile?>(null)
        private set

    fun login(authRequest: AuthRequest) {
        viewModelScope.launch {
            try {
                val authResponse = RetrofitClient.authService.authPassword(authRequest)
                authResponse.token?.let { token ->
                    tokenManager.saveToken(token)
                    authResponse.record.id?.let { id ->
                        tokenManager.saveUserId(id)
                    }
                    userProfile = authResponse.record
                    _isAuthorized.value = true
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Login error: ${e.message}")
            }
        }
    }

    fun fetchProfile() {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                val userId = tokenManager.userIdFlow()
                if (token != null && userId != null) {
                    userProfile = RetrofitClient.authService.getProfile(token, userId)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Fetch profile error: ${e.message}")
            }
        }
    }

    fun logout() {
        _isAuthorized.value = false
        userProfile = null
    }
}
