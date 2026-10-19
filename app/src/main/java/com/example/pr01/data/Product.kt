package com.example.pr01.data

data class Product(
    val id: Int? = null,
    val title: String,
    val price: Int,
    val category: String,
    val stock: Int
)