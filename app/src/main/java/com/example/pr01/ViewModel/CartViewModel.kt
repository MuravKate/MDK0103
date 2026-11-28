package com.example.pr01.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.Carts
import com.example.pr01.data.UpdateCartRequest
import com.example.pr01.service.RetrofitClient
import com.example.pr01.service.TokenManager
import kotlinx.coroutines.launch

class CartViewModel(val tokenManager: TokenManager) : ViewModel() {

    private val _cartsList = mutableStateListOf<Carts>()
    val cartsList = _cartsList

    var selectedCart by mutableStateOf<Carts?>(null)
        private set

    var isUpdateSuccess by mutableStateOf(false)
        private set

    fun getCarts() {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                token?.let {
                    val cartResponse = RetrofitClient.cartServiceAuth.getCartsAuth(it)
                    cartsList.clear()
                    cartsList.addAll(cartResponse.items)
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "getCarts error: ${e.message}")
            }
        }
    }

    fun getCartById(id: String) {
        isUpdateSuccess = false
        selectedCart = null
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                token?.let {
                    selectedCart = RetrofitClient.cartServiceAuth.getCartById(it, id)
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "getCartById error: ${e.message}")
            }
        }
    }

    fun updateCart(id: String, newTotal: Double) {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                token?.let {
                    RetrofitClient.cartServiceAuth.updateCart(
                        token = it,
                        id = id,
                        request = UpdateCartRequest(total = newTotal)
                    )
                    // Обновляем список и сигнализируем об успешном сохранении
                    getCarts()
                    isUpdateSuccess = true
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "updateCart error: ${e.message}")
            }
        }
    }

    fun resetUpdateState() {
        isUpdateSuccess = false
    }
}
