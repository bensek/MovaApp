package com.amalitech.movaapp.data.remote.dto

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.core.util.Constants
import com.amalitech.movaapp.core.util.formatDateFromSimpleFormat
import com.amalitech.movaapp.core.util.genreString
import com.amalitech.movaapp.core.util.spokenLanguagesString
import com.amalitech.movaapp.data.remote.dto.MovieDto
import com.amalitech.movaapp.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor(): Function1<List<MovieDto>, List<Movie>> {
    override fun invoke(movieDtos: List<MovieDto>): List<Movie> {
        return movieDtos
            .map { dto ->
                dto.toMovie()
            }
    }
}

@SuppressLint("NewApi")
fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = Constants.IMAGE_BASE_URL + posterPath,
        backDropUrl = Constants.IMAGE_BASE_URL + backdropPath,
        rating = String.format("%.1f", voteAverage),
        genre = genreString(genres),
        releaseDate = formatDateFromSimpleFormat(releaseDate),
        age = if (adult) "18+" else "13+",
        language = spokenLanguagesString(spokenLanguages),
        status = status ?: ""
    )
}

