package com.example.pr01.service

import com.example.pr01.data.TodosResponse
import retrofit2.http.GET

interface TodosInterface {
    @GET("todos")
    suspend fun getTodos(): TodosResponse
}