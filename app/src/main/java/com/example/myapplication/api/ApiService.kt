package com.example.myapplication.api

import com.example.myapplication.model.Films
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=fa844ca3beb3b19b4a871a3662894d27&language=ru-RU&page=1")
    suspend fun getPopular(): Response<Films>

    @GET("3/movie/now_playing?api_key=fa844ca3beb3b19b4a871a3662894d27&language=en-US&page=1")
    suspend fun getNowPlaying(): Response<Films>
}