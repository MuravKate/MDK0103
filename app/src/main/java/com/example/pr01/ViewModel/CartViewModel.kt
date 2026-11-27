package com.example.pr01.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.Carts
import com.example.pr01.service.RetrofitClient
import com.example.pr01.service.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartViewModel(val tokenManager: TokenManager) : ViewModel() {

    private val _cartsList = MutableStateFlow<List<Carts>>(listOf())
    val cartsList = _cartsList

    fun getCarts() {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                token?.let {
                    val cartResponse = RetrofitClient.cartServiceAuth.getCartsAuth(it)
                    cartsList.value = cartResponse.items

                    for (item in cartResponse.items) {
                        Log.d(
                            "CartViewModel",
                            "id: ${item.id}" +
                                    "\nproducts: ${item.totalProducts}" +
                                    "\ntotal: ${item.total}" +
                                    "\ncreated: ${item.created}" +
                                    "\nupdated: ${item.updated}"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", e.message.toString())
            }
        }
    }
}
