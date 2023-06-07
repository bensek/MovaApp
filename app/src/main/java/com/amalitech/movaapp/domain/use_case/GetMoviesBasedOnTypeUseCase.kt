package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.MovieType
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesBasedOnTypeUseCase {
    suspend operator fun invoke(movieType: MovieType): Result<List<Movie>>
}