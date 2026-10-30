package com.example.pr01.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr01.service.RetrofitClient
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    fun deleteRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.recipesService.deleteRecipe(id)

                Log.d(
                    "RecipeViewModel",
                    "РЕЦЕПТ УСПЕШНО УДАЛЕН!" +
                            "\nИдентификатор: ${response.id}" +
                            "\nНазвание: ${response.name}" +
                            "\nisDeleted: ${response.isDeleted}" +
                            "\ndeletedOn: ${response.deletedOn}"
                )
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Ошибка удаления рецепта: ${e.message}", e)
            }
        }
    }
}
