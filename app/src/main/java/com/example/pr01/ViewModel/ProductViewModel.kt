package com.example.pr01.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.Product
import com.example.pr01.service.RetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    fun loadAndModifyProduct() {
        viewModelScope.launch {
            try {
                val productId = 25

                Log.d("PRODUCT", "ЗАГРУЗКА ДАННЫХ (ID=$productId)")

                val originalProduct = RetrofitClient.productAPI.getProduct(productId)
                Log.d("PRODUCT", "ДО РЕДАКТИРОВАНИЯ:")
                Log.d("PRODUCT", "  id: ${originalProduct.id}")
                Log.d("PRODUCT", "  title: ${originalProduct.title}")
                Log.d("PRODUCT", "  price: ${originalProduct.price}")
                Log.d("PRODUCT", "  category: ${originalProduct.category}")
                Log.d("PRODUCT", "  stock: ${originalProduct.stock}")

                Log.d("PRODUCT", "ИЗМЕНЕНИЕ ДАННЫХ")


                val updatedProduct = originalProduct.copy(
                    title = "Офисное кресло Comfort-Т",
                    price = 14200.0,
                    category = "Офисная мебель",
                    stock = 3
                )


                val serverResponse = RetrofitClient.productAPI.updateProduct(productId, updatedProduct)
                Log.d("PRODUCT", "ПОСЛЕ РЕДАКТИРОВАНИЯ (ответ сервера):")
                Log.d("PRODUCT", "  id: ${serverResponse.id}")
                Log.d("PRODUCT", "  title: ${serverResponse.title}")
                Log.d("PRODUCT", "  price: ${serverResponse.price}")
                Log.d("PRODUCT", "  category: ${serverResponse.category}")
                Log.d("PRODUCT", "  stock: ${serverResponse.stock}")

                Log.d("PRODUCT", "ГОТОВО")

            } catch (e: Exception) {
                Log.e("PRODUCT", "Ошибка выполнения: ${e.localizedMessage}", e)
            }
        }
    }
}