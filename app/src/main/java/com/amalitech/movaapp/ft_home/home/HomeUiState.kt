package com.amalitech.movaapp.ft_home.home

import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.model.Movie

data class HomeUiState(
    val isLoading: Boolean = false,
    val movies: HomeMovies? = null,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)

sealed class UiState<T>(val data: T?, val error: String?){

    object Loading: UiState()
}