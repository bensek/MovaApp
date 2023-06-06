package com.amalitech.movaapp.ft_home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.data.repository.MoviesRepositoryImpl
import com.amalitech.movaapp.domain.repository.MoviesRepository
import com.amalitech.movaapp.domain.use_case.GetHomeMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMoviesUseCase: GetHomeMoviesUseCase
): ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
//        loadMovies()
        showMovies()
    }

    private fun showMovies() {
        viewModelScope.launch {
            getHomeMoviesUseCase().collect { resource ->
                when(resource) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = resource.data
                        )
                    }
                    is Resource.Error -> _uiState.update { it.copy(isLoading = false, hasError = true, errorMessage = resource.message!! ) }
                }

            }
        }
    }

//    fun loadMovies() {
//            _uiState.update { it.copy(isLoading = true) }
//
//            val popularDeferred = viewModelScope.async {
//                repo.getPopularMovies()
//            }
//            val topRatedDeferred = viewModelScope.async {
//                repo.getTopRatedMovies()
//            }
//            val upcomingDeferred = viewModelScope.async {
//                repo.getUpcomingMovies()
//            }
//            val featuredDeferred = viewModelScope.async {
//                repo.getFeaturedMovie()
//            }
//
//            viewModelScope.launch {
//                val popular = popularDeferred.await()
//                val topRated = topRatedDeferred.await()
//                val upcoming = upcomingDeferred.await()
//                val featured = featuredDeferred.await()
//
//                _uiState.update { state ->
//                    state.copy(
//                        popular = popular,
//                        topRated = topRated,
//                        upcoming = upcoming,
//                        featured = featured,
//                        isLoading = false
//                    )
//                }
//            }
//         }
}