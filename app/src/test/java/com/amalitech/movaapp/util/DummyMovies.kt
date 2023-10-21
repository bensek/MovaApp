package com.amalitech.movaapp.util

import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.data.remote.dto.toMovie
import com.amalitech.movaapp.domain.model.Movie


val movieDto = MovieDto(
    id = 640146,
    title = "Ant-Man and the Wasp: Quantumania",
    overview = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
    releaseDate = "2023-02-15",
    voteAverage = 6.5,
    voteCount = 1886,
    posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
    backdropPath = "/gMJngTNfaqCSCqGD4y8lVMZXKDn.jpg",
    genreIds = listOf(28,12,878),
    genres = emptyList(),
    status = "",
    adult = false,
    spokenLanguages = emptyList()
)

fun generateDummyMovies(): List<Movie> {
    val list = mutableListOf<MovieDto>()
    repeat(5) {
        list.add(movieDto)
    }

    val mapper = MovieMapper()
    return mapper(list)
}

fun generateSingleMovie(): Movie {
    return movieDto.toMovie()
}
