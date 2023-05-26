package com.amalitech.movaapp.ft_home


sealed class MovieType(val name: String) {
    object Popular: MovieType("Popular")
    object TopRated: MovieType("Top Rated")
    object Upcoming: MovieType("Upcoming")
    object NowPlaying: MovieType("Now Playing")
}
