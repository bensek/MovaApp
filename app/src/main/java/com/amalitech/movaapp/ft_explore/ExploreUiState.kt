package com.amalitech.movaapp.ft_explore

import com.amalitech.movaapp.domain.model.Movie

data class ExploreUiState(
    var movies: List<Movie> = emptyList(),
    var isLoading: Boolean = false,
    var errorMsg: String = "",
    var hasError: Boolean = false,
    var searchQuery: String = ""
)
