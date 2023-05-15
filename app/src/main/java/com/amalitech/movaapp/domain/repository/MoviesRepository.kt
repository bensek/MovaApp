package com.amalitech.movaapp.domain.repository

import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.domain.model.Movie

interface MoviesRepository {

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun getFeaturedMovie(): Movie

}