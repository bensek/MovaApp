package com.amalitech.movaapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.core.util.*
import com.amalitech.movaapp.data.remote.ApiInterface
import com.amalitech.movaapp.data.remote.dto.*
import com.amalitech.movaapp.data.remote.retrofit.ApiResult
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: ApiInterface,
    private val mapper: MovieMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): MoviesRepository {
    override suspend fun getPopularMovies(): Result<List<Movie>> = withContext(dispatcher) {
        when(val result = api.fetchPopularMovies()) {
            is ApiResult.Success -> {
                val list = mapper((result.data as ApiData).results)
                Result.success(list)
            }
            is ApiResult.Error -> {
                Result.failure(Exception("An error occurred"))
            }
            is ApiResult.Exception -> {
                Result.failure(result.exception)
                // Fetch from cache
            }
        }
    }

    override suspend fun getTopMovies(): Result<List<Movie>> = withContext(dispatcher) {
        when(val result = api.fetchTopMovies()) {
            is ApiResult.Success -> {
                val list = mapper((result.data as ApiData).results)
                Result.success(list)
            }
            is ApiResult.Error -> {
                Result.failure(Exception("An error occurred"))
            }
            is ApiResult.Exception -> {
                Result.failure(result.exception)
                // Fetch from cache
            }
        }
    }

    override suspend fun getUpcomingMovies(): Result<List<Movie>> = withContext(dispatcher) {
        when(val result = api.fetchUpcomingMovies()) {
            is ApiResult.Success -> {
                val list = mapper((result.data as ApiData).results)
                Result.success(list)
            }
            is ApiResult.Error -> {
                Result.failure(Exception("An error occurred"))
            }
            is ApiResult.Exception -> {
                Result.failure(result.exception)
                // Fetch from cache
            }
        }
    }

    override suspend fun getNowPlayingMovies(): Result<List<Movie>> = withContext(dispatcher) {
        when(val result = api.fetchNowPlayingMovies()) {
            is ApiResult.Success -> {
                val list = mapper((result.data as ApiData).results)
                Result.success(list)
            }
            is ApiResult.Error -> {
                Result.failure(Exception("An error occurred"))
            }
            is ApiResult.Exception -> {
                Result.failure(result.exception)
                // Fetch from cache
            }
        }
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        return withContext(dispatcher) {
            api.getMovieDetails(id)
                .toMovie()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getMovieVideos(id: Int): List<Video> {
        return withContext(dispatcher) {
            api.getMovieVideos(id)
                .results
                .map {
                    it.toVideo()
                }
        }
    }

    override suspend fun getSimilarMovies(id: Int): List<Movie> {
        return withContext(dispatcher) {
            api.getSimilarMovies(id)
                .results
                .map {
                    it.toMovie()
                }
        }
    }

    override suspend fun getMovieCredits(id: Int): List<Credit> {
        return withContext(dispatcher) {
            val credit = api.getMovieCredits(id)
                .cast
                .map {
                    it.toCredit()
                }

            Log.v("Tag", "Credit: ${credit?.size}")

            credit
        }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return withContext(dispatcher) {
            api.searchMovie(query)
                .results
                .map {
                    it.toMovie()
                }
        }
    }
}