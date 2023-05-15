package com.amalitech.movaapp.ft_home.home

import com.amalitech.movaapp.domain.model.Movie

data class HomeUiState(
    val isLoading: Boolean = false,
    val featured: Movie? = null,
    val popular: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList()
)
