package com.amalitech.movaapp.domain.repository

import com.amalitech.movaapp.data.remote.dto.ApiData
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video
import retrofit2.Response

interface MoviesRepository {

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun fetchTopMovies(): Response<ApiData>

    suspend fun getPopularMovies(): List<Movie>

    suspend fun fetchPopularMovies(): Response<ApiData>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun fetchUpcomingMovies(): Response<ApiData>

    suspend fun fetchNowPlayingMovies(): Response<ApiData>

    suspend fun getFeaturedMovie(): Movie

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getMovieVideos(id: Int): List<Video>

    suspend fun getSimilarMovies(id: Int): List<Movie>

    suspend fun getMovieCredits(int: Int): List<Credit>

    suspend fun searchMovies(query: String): List<Movie>


}