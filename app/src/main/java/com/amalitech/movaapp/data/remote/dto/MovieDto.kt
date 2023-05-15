package com.amalitech.movaapp.data.remote.dto

import com.amalitech.movaapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int = 0,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        title = title,
        description = overview,
        imageUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        rating = voteAverage
    )
}
