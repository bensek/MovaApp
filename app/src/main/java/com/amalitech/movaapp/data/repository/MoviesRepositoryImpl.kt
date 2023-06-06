package com.amalitech.movaapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.data.remote.ApiInterface
import com.amalitech.movaapp.data.remote.dto.ApiData
import com.amalitech.movaapp.data.remote.dto.toCredit
import com.amalitech.movaapp.data.remote.dto.toMovie
import com.amalitech.movaapp.data.remote.dto.toVideo
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: ApiInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): MoviesRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return withContext(dispatcher) {
            api.getPopularMovies()
                .results
                .map {
                    it.toMovie()
                }
        }
    }

    override suspend fun fetchPopularMovies(): Response<ApiData> {
        return api.fetchPopularMovies()
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

    override suspend fun fetchTopMovies(): Response<ApiData> {
        return api.fetchTopMovies()
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

    override suspend fun fetchUpcomingMovies(): Response<ApiData> {
        return api.fetchUpcomingMovies()
    }

    override suspend fun fetchNowPlayingMovies(): Response<ApiData> {
        return api.fetchNowPlayingMovies()
    }

    override suspend fun getFeaturedMovie(): Movie {
        return withContext(dispatcher) {
            api.getNowPlayingMovies()
                .results[5]
                .toMovie()
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