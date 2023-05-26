package com.amalitech.movaapp.ft_explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amalitech.movaapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private var _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMovies(generateRandomChar())
    }

    private fun loadMovies(query: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val movies = repository.searchMovies(query)
                Log.v("TAG", "Movies -> ${movies.size}")
                _uiState.update {
                    it.copy(
                        movies = movies,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        hasError = true,
                        errorMsg = e.localizedMessage,
                        isLoading = false
                    ) }
            }

        }


    }

    private fun generateRandomChar(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val randomIndex = Random.nextInt(0, charPool.size)
        return charPool[randomIndex].toString()
    }

    fun onSearchQueryChanged(query: String, showLoading: Boolean = false) {
        _uiState.update {
            it.copy(searchQuery = query, isLoading = showLoading)
        }
        loadMovies(_uiState.value.searchQuery)
    }
}