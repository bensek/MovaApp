package com.amalitech.movaapp.data.remote

import com.amalitech.movaapp.data.remote.dto.ApiResponse
import com.amalitech.movaapp.domain.model.Movie
import retrofit2.http.GET

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

}