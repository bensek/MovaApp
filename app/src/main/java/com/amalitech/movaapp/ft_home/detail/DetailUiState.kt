package com.amalitech.movaapp.ft_home.detail

import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video

data class DetailUiState(
    val movie: Movie? = null,
    val trailers: List<Video> = emptyList(),
    val similar: List<Movie> = emptyList(),
    val credits: List<Credit> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val errorMsg: String = ""
)
