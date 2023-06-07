package com.amalitech.movaapp.ft_home.grid

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.core.util.createMovieTypeFromString
import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.use_case.GetMoviesBasedOnTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMoviesBasedOnTypeUseCase: GetMoviesBasedOnTypeUseCase
): ViewModel() {

    private val typeArg = checkNotNull(savedStateHandle.get<String>("type"))

    private var _uiState: MutableStateFlow<GridUiState> = MutableStateFlow(GridUiState())
    val uiState = _uiState.asStateFlow()

    init {
        showMovies()
    }

    private fun showMovies() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result: Result<List<Movie>> = getMoviesBasedOnTypeUseCase(createMovieTypeFromString(typeArg))
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movies = result.getOrNull()!!,
                        hasError = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        hasError = true,
                        errorMessage = result.exceptionOrNull()?.message!!
                    )
                }
            }
        }
    }
}