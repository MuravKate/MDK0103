package com.example.pr01.data

data class DeletedRecipeResponse(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val difficulty: String,
    val caloriesPerServing: Int,
    val isDeleted: Boolean,
    val deletedOn: String
)
