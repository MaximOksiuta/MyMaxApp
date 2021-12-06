package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Films(
    val page: Int,
    @SerializedName("results")
    val films: List<Film>,
    val total_pages: Int,
    val total_results: Int
)