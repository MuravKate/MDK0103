package com.example.pr01.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.PostRequest
import com.example.pr01.service.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    fun addPost() {
        viewModelScope.launch {
            try {
                val request = PostRequest(
                    title = "Уют в каждой детали: встречайте нашу новинку!",
                    body = "Мы знаем, как важно возвращаться туда, где тепло и спокойно. Наша новая коллекция ароматических свечей из соевого воска создана именно для таких моментов.",
                    tags = listOf("декор дома", "уют в доме", "аромасвечи", "подарок девушке", "ручная работа", "новинка"),
                    views = 0
                )
                val response = RetrofitClient.retrofitAPI.addPost(request)
                println("ID: ${response.id}")
                println("Title: ${response.title}")
                println("Body: ${response.body}")
                println("Tags: ${response.tags.joinToString(", ")}")
                println("Views: ${response.views}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}