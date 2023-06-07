package com.amalitech.movaapp.domain.repository

import com.amalitech.movaapp.core.util.MovieType
import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video

interface MoviesRepository {

    suspend fun getTopMovies(): Result<List<Movie>>

    suspend fun getPopularMovies(): Result<List<Movie>>

    suspend fun getUpcomingMovies(): Result<List<Movie>>

    suspend fun getNowPlayingMovies(): Result<List<Movie>>

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getMovieVideos(id: Int): List<Video>

    suspend fun getSimilarMovies(id: Int): List<Movie>

    suspend fun getMovieCredits(int: Int): List<Credit>

    suspend fun searchMovies(query: String): List<Movie>


}