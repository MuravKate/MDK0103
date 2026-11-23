package com.example.pr01.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.Carts
import com.example.pr01.service.RetrofitClient
import com.example.pr01.service.TokenManager
import kotlinx.coroutines.launch

class CartViewModel(val tokenManager: TokenManager) : ViewModel() {

    val cartsList = mutableStateListOf<Carts>()

    fun getCarts() {
        viewModelScope.launch {
            try {
                val token = tokenManager.tokenFlow()
                token?.let {
                    val cartResponse = RetrofitClient.cartServiceAuth.getCartsAuth(it)
                    cartsList.clear()
                    cartsList.addAll(cartResponse.items)

                    for (item in cartResponse.items) {
                        Log.d(
                            "CartViewModel",
                            "id: ${item.id}" +
                                    "\nuserId: ${item.userId}" +
                                    "\nproducts: ${item.products}" +
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
