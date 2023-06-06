package com.amalitech.movaapp.ft_home.grid

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.core.util.createMovieTypeFromString
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
        viewModelScope.launch {
            val movieType = createMovieTypeFromString(typeArg)
            getMoviesBasedOnTypeUseCase(movieType).collect { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                movies = resource.data!!
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.value = GridUiState(
                            hasError = true,
                            errorMessage = resource.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

//    private fun loadMovies() {
//            _uiState.update { it.copy(isLoading = true) }
//
//            viewModelScope.launch {
//                var list = emptyList<Movie>()
//
//                when (movieType) {
//                    MovieType.Popular.name -> {
//                        list = repo.getPopularMovies()
//                    }
//                    MovieType.TopRated.name -> {
//                        list = repo.getTopRatedMovies()
//                    }
//                    MovieType.Upcoming.name -> {
//                        list = repo.getUpcomingMovies()
//                    }
//                }
//
//                _uiState.update { state ->
//                    state.copy(
//                        movies = list,
//                        isLoading = false
//                    )
//                }
//            }
//         }
}