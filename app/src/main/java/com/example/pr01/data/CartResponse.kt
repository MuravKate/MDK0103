package com.example.pr01.data

data class CartResponse(
    val items: List<Carts>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)
