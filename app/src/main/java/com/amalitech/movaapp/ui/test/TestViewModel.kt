package com.amalitech.movaapp.ui.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.movaapp.domain.repository.MoviesRepository
import com.amalitech.movaapp.domain.use_case.GetMoviesBasedOnTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    val repository: MoviesRepository
): ViewModel() {

    var text by mutableStateOf("")

    init {
        viewModelScope.launch {
            val result = repository.getPopularMovies()
           if (result.isSuccess) {
               text = result.getOrNull().toString()!!
           } else {
               text = result.exceptionOrNull().toString()
           }
        }

    }
}