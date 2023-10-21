package com.amalitech.movaapp.ft_home.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.core.util.PLog
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.data.repository.MoviesRepositoryImpl
import com.amalitech.movaapp.domain.model.HomeMovies
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
        showMovies()
    }

    fun showMovies() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getHomeMoviesUseCase()
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movies = result.getOrNull(),
                        hasError = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        hasError = true,
                        errorMessage = result.exceptionOrNull().toString()
                    )
                }
            }
        }
    }

    fun errorShown() {
        _uiState.update {
            it.copy(
                hasError = false,
                errorMessage = ""
            )
        }
    }
}