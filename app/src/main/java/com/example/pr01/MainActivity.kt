package com.example.pr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pr01.ViewModel.CartViewModel
import com.example.pr01.ViewModel.LoginViewModel
import com.example.pr01.screens.CartsScreen
import com.example.pr01.screens.LoginScreen
import com.example.pr01.service.TokenManager
import com.example.pr01.ui.theme.Pr01Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenManager = TokenManager(this)

        val cartViewModel: CartViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return CartViewModel(tokenManager) as T
                }
            }
        }

        val loginViewModel: LoginViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(tokenManager, cartViewModel) as T
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
                    CartsScreen(cartViewModel)
                }


            }
        }
    }
}
