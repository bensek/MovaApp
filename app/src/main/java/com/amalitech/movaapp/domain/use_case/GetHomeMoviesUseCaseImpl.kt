package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.*

class GetHomeMoviesUseCaseImpl(
    private val repository: MoviesRepository
): GetHomeMoviesUseCase {
    override suspend fun invoke(): Result<HomeMovies> {

        return coroutineScope {
            val popularDeferred = async { repository.getPopularMovies() }
            val topDeferred = async { repository.getTopMovies() }
            val upcomingDeferred = async { repository.getUpcomingMovies() }
            val nowDeferred = async { repository.getNowPlayingMovies() }

            val popularResult = popularDeferred.await()
            val topResult = topDeferred.await()
            val upcomingResult = upcomingDeferred.await()
            val nowResult = nowDeferred.await()

            if (popularResult.isSuccess && topResult.isSuccess && upcomingResult.isSuccess && nowResult.isSuccess) {
                Result.success(HomeMovies(
                    popular = popularResult.getOrNull()!!,
                    topRated = topResult.getOrNull()!!,
                    upcoming = upcomingResult.getOrNull()!!,
                    featured = nowResult.getOrNull()?.first()!!
                ))
            } else {
                Result.failure(Exception("An error occurred"))
            }
        }

    }

}