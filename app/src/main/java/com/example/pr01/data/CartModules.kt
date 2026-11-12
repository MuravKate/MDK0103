package com.example.pr01.data

data class CartsResponse(
    val carts: List<Carts>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Cart(
    val id: Int,
    val products: List<CartProduct>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)

data class CartsProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double
)
