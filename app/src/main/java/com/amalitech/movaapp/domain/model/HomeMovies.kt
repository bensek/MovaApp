package com.amalitech.movaapp.domain.model

data class HomeMovies(
    val popularMovies: List<Movie>,
    val topRatedMovies: List<Movie>,
    val upcomingMovies: List<Movie>,
    val featuredMovie: Movie
)
