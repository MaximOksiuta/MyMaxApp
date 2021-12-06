package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.Films
import retrofit2.Response

class Repository {

    suspend fun getPopular(): Response<Films>{
        return RetrofitInstance.api.getPopular()
    }

    suspend fun getNowPlaying(): Response<Films>{
        return RetrofitInstance.api.getNowPlaying()
    }
}