package com.example.pr01.service

import com.example.pr01.data.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductInterface {
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Product

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: Product
    ): Product
}