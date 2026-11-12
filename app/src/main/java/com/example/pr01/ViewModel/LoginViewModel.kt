package com.example.pr01.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.AuthRequest
import com.example.pr01.service.RetrofitClient
import com.example.pr01.service.TokenManager
import kotlinx.coroutines.launch

class LoginViewModel(
    val tokenManager: TokenManager,
    val cartViewModel: CartViewModel
) : ViewModel() {

    fun login(authRequest: AuthRequest) {
        viewModelScope.launch {
            try {
                val authResponse = RetrofitClient.authService.authPassword(authRequest)
                authResponse.token?.let {
                    tokenManager.saveToken(it)
                    cartViewModel.getCarts()
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", e.message.toString())
            }
        }
    }
}
