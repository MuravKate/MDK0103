package com.example.pr01.data

data class PostResponse(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val views: Int
)