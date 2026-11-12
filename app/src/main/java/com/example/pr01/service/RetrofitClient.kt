package com.example.pr01.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

object RetrofitClient {

    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.207.106.59", 3128))

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val httpClient = OkHttpClient.Builder()
        .proxy(proxy)
        .addInterceptor(loggingInterceptor)
        .build()

    private val api: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.207.106")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = api.create(AuthService::class.java)

    val cartServiceAuth: CartServiceAuth = api.create(CartServiceAuth::class.java)
}
