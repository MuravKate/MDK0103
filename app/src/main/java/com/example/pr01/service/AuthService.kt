package com.example.pr01.service

import com.example.pr01.data.AuthRequest
import com.example.pr01.data.AuthResponse
import com.example.pr01.data.Profile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("users/auth-with-password")
    suspend fun authPassword(@Body authRequest: AuthRequest): AuthResponse

    @GET("users/records/{id}")
    suspend fun getProfile(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Profile
}
