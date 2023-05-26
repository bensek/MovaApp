package com.amalitech.movaapp.data.remote

import com.amalitech.movaapp.data.remote.dto.ApiResponse
import com.amalitech.movaapp.data.remote.dto.CreditApiResponse
import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.data.remote.dto.VideoApiResponse
import com.amalitech.movaapp.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/now_playing")
    suspend fun getLatestMovies(): ApiResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): ApiResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): ApiResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ApiResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): ApiResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): VideoApiResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): ApiResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): CreditApiResponse


    // Explore Screen

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): ApiResponse
}