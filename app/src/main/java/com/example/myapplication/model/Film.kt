package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Film(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    @SerializedName("overview")
    val description: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val imageURL: String,
    @SerializedName("release_date")
    val year: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double,
    val vote_count: Int
)