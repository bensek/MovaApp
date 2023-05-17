package com.amalitech.movaapp.domain.model

data class Movie(
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val backDropUrl: String,
    val genre: String = "",
    val rating: String,
    val trailers: List<Video> = emptyList(),
    val releaseDate: String,
    val age: String,
    val language: String,
    val status: String
)
