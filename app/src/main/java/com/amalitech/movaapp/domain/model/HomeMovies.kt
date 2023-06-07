package com.amalitech.movaapp.domain.model

data class HomeMovies(
    val popular: List<Movie>,
    val topRated: List<Movie>,
    val upcoming: List<Movie>,
    val featured: Movie
)
