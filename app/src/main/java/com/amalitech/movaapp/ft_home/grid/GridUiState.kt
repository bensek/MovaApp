package com.amalitech.movaapp.ft_home.grid

import com.amalitech.movaapp.domain.model.Movie

data class GridUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList()
)
