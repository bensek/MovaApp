package com.amalitech.movaapp.data.remote

import com.amalitech.movaapp.data.remote.dto.ApiData
import com.amalitech.movaapp.data.remote.dto.CreditApiResponse
import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.data.remote.dto.VideoApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/now_playing")
    suspend fun getLatestMovies(): ApiData

    @GET("movie/popular")
    suspend fun getPopularMovies(): ApiData

    @GET("movie/popular")
    suspend fun fetchPopularMovies(): Response<ApiData>

    @GET("movie/top_rated")
    suspend fun fetchTopMovies(): Response<ApiData>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(): Response<ApiData>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): ApiData

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ApiData

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): ApiData

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): VideoApiResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): ApiData

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): CreditApiResponse


    // Explore Screen

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): ApiData
}