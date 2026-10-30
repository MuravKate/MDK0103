package com.example.pr01.service

import com.example.pr01.data.DeletedRecipeResponse
import retrofit2.http.DELETE
import retrofit2.http.Path

interface RecipeService {

    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") recipeId: Int): DeletedRecipeResponse
}
