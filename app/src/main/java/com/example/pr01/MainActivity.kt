package com.example.pr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pr01.ViewModel.LoginViewModel
import com.example.pr01.screens.LoginScreen
import com.example.pr01.screens.ProfileScreen
import com.example.pr01.service.TokenManager
import com.example.pr01.ui.theme.Pr01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenManager = TokenManager(this)

        val loginViewModel: LoginViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(tokenManager) as T
                }
            }
        }

        setContent {
            Pr01Theme {
                var isLoggedIn by remember { mutableStateOf(false) }

                if (!isLoggedIn) {
                    LoginScreen(
                        viewModel = loginViewModel,
                        onLoginSuccess = { isLoggedIn = true }
                    )
                } else {
                    ProfileScreen(
                        viewModel = loginViewModel,
                        onLogout = {
                            loginViewModel.logout()
                            isLoggedIn = false
                        }
                    )
                }
            }
        }
    }
}
