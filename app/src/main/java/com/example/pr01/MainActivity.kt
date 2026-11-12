package com.example.pr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pr01.ViewModel.CartViewModel
import com.example.pr01.ViewModel.LoginViewModel
import com.example.pr01.data.AuthRequest
import com.example.pr01.service.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenManager = TokenManager(applicationContext)
        setContent {

            val cartViewModel: CartViewModel by viewModels {
                viewModelFactory {
                    initializer {
                        CartViewModel(tokenManager)
                    }
                }
            }

            val loginViewModel: LoginViewModel by viewModels {
                viewModelFactory {
                    initializer {
                        LoginViewModel(tokenManager, cartViewModel)
                    }
                }
            }

            val authRequest = AuthRequest(
                identity = "kate09102007_new@mail.com",
                password = "Password1234!"
            )
            loginViewModel.login(authRequest)
        }
    }
}
