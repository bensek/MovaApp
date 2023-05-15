package com.amalitech.movaapp.domain.model

data class Movie(
    val title: String,
    val description: String,
    val imageUrl: String,
    val genre: String = "",
    val rating:Double
)
