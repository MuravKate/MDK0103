package com.example.pr01.data

data class PostRequest(
    val title: String,
    val body: String,
    val tags: List<String>
)