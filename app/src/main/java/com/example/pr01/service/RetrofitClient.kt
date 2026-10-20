package com.example.pr01.service

import com.example.pr01.data.PostResponse
import com.example.pr01.data.PostRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

object RetrofitClient {

    private val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.207.106.59", 3128))
    private val httpClient = OkHttpClient.Builder()
        .proxy(proxy)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postAPI: PostInterface = retrofit.create(PostInterface::class.java)
    val productAPI: ProductInterface = retrofit.create(ProductInterface::class.java)

    val todosAPI: TodosInterface = retrofit.create(TodosInterface::class.java)
}

