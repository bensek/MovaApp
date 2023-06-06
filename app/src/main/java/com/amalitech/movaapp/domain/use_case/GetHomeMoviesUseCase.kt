package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.domain.model.HomeMovies
import kotlinx.coroutines.flow.Flow

interface GetHomeMoviesUseCase {
    suspend operator fun invoke(): Flow<Resource<HomeMovies>>
}