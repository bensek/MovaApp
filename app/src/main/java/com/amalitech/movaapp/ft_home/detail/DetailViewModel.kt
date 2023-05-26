package com.amalitech.movaapp.ft_home.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.data.repository.MoviesRepositoryImpl
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int?
): ViewModel() {
    private val repo: MoviesRepository = MoviesRepositoryImpl()

    private var _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val movie = repo.getMovieDetails(movieId!!)
                val videos = repo.getMovieVideos(movieId!!)
                val similar = repo.getSimilarMovies(movieId!!)
                val credits = repo.getMovieCredits(movieId!!)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movie = movie,
                        isFailure = false,
                        trailers = videos,
                        similar = similar,
                        credits = credits
                    ) }
            } catch (e: Exception) {
                Log.e("TAG", "Error -> ${e.message}")
                _uiState.update {
                    it.copy(isFailure = true, errorMsg = e.message.toString())
                }
            }
        }
    }

}