package com.amalitech.movaapp.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.core.util.Constants.IMAGE_BASE_URL
import com.amalitech.movaapp.core.util.formatDateFromSimpleFormat
import com.amalitech.movaapp.core.util.genreString
import com.amalitech.movaapp.core.util.spokenLanguagesString
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
    val genreIds: List<Int>,
    val genres: List<GenreDto>,
    val status: String?,
    val adult: Boolean,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>?
)
