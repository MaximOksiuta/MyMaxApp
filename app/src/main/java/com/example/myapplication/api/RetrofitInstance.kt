package com.example.myapplication.api

import com.example.myapplication.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.create

object RetrofitInstance{
    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}