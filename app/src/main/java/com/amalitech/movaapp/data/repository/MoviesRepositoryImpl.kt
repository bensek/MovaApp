package com.amalitech.movaapp.data.repository

import android.util.Log
import com.amalitech.movaapp.data.remote.ApiInterface
import com.amalitech.movaapp.data.remote.RetrofitService
import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.data.remote.dto.toMovie
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
): MoviesRepository {

    private val api:ApiInterface = RetrofitService.apiInterface
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getPopularMovies(): List<Movie> {
        return withContext(dispatcher) {
            api.getPopularMovies()
                .results
                .map {
                    it.toMovie()
                }
        }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return withContext(dispatcher) {
            api.getTopRatedMovies()
                .results
                .map {
                    it.toMovie()
                }
        }
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return withContext(dispatcher) {
            api.getUpcomingMovies()
                .results
                .map {
                    it.toMovie()
                }
        }
    }

    override suspend fun getFeaturedMovie(): Movie {
        return withContext(dispatcher) {
            api.getNowPlayingMovies()
                .results[5]
                .toMovie()
        }
    }

}