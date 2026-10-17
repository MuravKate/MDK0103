package com.example.pr01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr01.ViewModel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoViewModel = viewModel()

            LaunchedEffect(Unit) {
                viewModel.fetchTodos()
            }

            viewModel.todosList.forEach { todo ->
                Log.d("TODO", "${todo.todo} — ${if (todo.completed) "выполнено" else "не выполнено"}")
            }
        }
    }
}
