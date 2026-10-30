package com.example.pr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr01.ViewModel.RecipeViewModel
import com.example.pr01.ui.theme.Pr01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr01Theme {
                val recipeViewModel: RecipeViewModel = viewModel()

                LaunchedEffect(Unit) {
                    // Вариант 5 требует удаления рецепта с ID 16
                    recipeViewModel.deleteRecipe(id = 16)
                }
            }
        }
    }
}
