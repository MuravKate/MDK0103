package com.example.pr01.service

import com.example.pr01.data.PostRequest
import com.example.pr01.data.PostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PostInterface {
    @POST("posts/add")
    suspend fun addPost(@Body request: PostRequest): PostResponse
}