package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.MovieType
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetMoviesBasedOnTypeUseCaseImpl(
    private val repository: MoviesRepository
): GetMoviesBasedOnTypeUseCase {

    override suspend fun invoke(movieType: MovieType): Result<List<Movie>> = when(movieType) {
                is MovieType.Popular -> repository.getPopularMovies()
                is MovieType.TopRated -> repository.getTopMovies()
                is MovieType.Upcoming -> repository.getUpcomingMovies()
                else -> throw IllegalArgumentException("Invalid movie type")
            }
}