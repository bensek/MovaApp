package com.amalitech.movaapp.ft_home.grid

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.data.repository.MoviesRepositoryImpl
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.repository.MoviesRepository
import com.amalitech.movaapp.ft_home.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class GridViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var repo: MoviesRepository
): ViewModel() {

    private val movieType = checkNotNull(savedStateHandle.get<String>("type"))

    private var _uiState: MutableStateFlow<GridUiState> = MutableStateFlow(GridUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
            _uiState.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                var list = emptyList<Movie>()

                when (movieType) {
                    MovieType.Popular.name -> {
                        list = repo.getPopularMovies()
                    }
                    MovieType.TopRated.name -> {
                        list = repo.getTopRatedMovies()
                    }
                    MovieType.Upcoming.name -> {
                        list = repo.getUpcomingMovies()
                    }
                }

                _uiState.update { state ->
                    state.copy(
                        movies = list,
                        isLoading = false
                    )
                }
            }
         }
}