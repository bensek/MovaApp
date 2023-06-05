package com.amalitech.movaapp.ft_home

const val POPULAR = "Popular"
const val TOP_RATED = "Top Rated"
const val UPCOMING = "Upcoming"
const val NOW_PLAYING = "Now Playing"

sealed class MovieType(val name: String) {
    object Popular: MovieType(POPULAR)
    object TopRated: MovieType(TOP_RATED)
    object Upcoming: MovieType(UPCOMING)
    object NowPlaying: MovieType(NOW_PLAYING)
}

fun createMovieTypeFromString(input: String): MovieType {
    return when(input) {
        POPULAR -> MovieType.Popular
        TOP_RATED -> MovieType.TopRated
        UPCOMING -> MovieType.Upcoming
        NOW_PLAYING -> MovieType.NowPlaying
        else -> MovieType.Popular
    }
}
