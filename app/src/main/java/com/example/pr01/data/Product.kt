package com.example.pr01.data

data class Product(
    val id: Int? = null,
    val title: String,
    val price: Double,
    val category: String,
    val stock: Int
)