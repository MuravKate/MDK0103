package com.example.pr01.service

import com.example.pr01.data.CartResponse
import retrofit2.http.GET

interface CartService {

    @GET("carts")
    suspend fun getCarts(): CartResponse
}
