package com.example.pr01.service

import com.example.pr01.data.CartResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CartServiceAuth {

    @GET("carts/records")
    suspend fun getCartsAuth(@Header("Authorization") token: String): CartResponse
}
