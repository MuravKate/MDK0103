package com.example.pr01.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.data.Todo
import com.example.pr01.service.RetrofitClient
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    val todosList = mutableStateListOf<Todo>()

    fun fetchTodos() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofitAPI.getTodos()
                todosList.clear()
                todosList.addAll(response.todos)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}