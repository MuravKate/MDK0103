package com.example.pr01.service

import com.example.pr01.data.CartResponse
import com.example.pr01.data.Carts
import com.example.pr01.data.UpdateCartRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface CartServiceAuth {

    @GET("carts/records")
    suspend fun getCartsAuth(
        @Header("Authorization") token: String
    ): CartResponse

    @GET("carts/records/{id}")
    suspend fun getCartById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Carts

    @PATCH("carts/records/{id}")
    suspend fun updateCart(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: UpdateCartRequest
    ): Carts
}
