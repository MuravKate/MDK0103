package com.example.pr01.data

data class Carts(
    val id: String? = null,
    val userId: String? = null,
    val products: List<String>? = null,
    val total: Double? = null,
    val created: String? = null,
    val updated: String? = null
)
