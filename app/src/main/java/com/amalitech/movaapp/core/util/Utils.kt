package com.amalitech.movaapp.core.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.util.Constants.DATE_FORMAT
import com.amalitech.movaapp.core.util.Constants.IMAGE_BASE_URL
import com.amalitech.movaapp.data.remote.dto.GenreDto
import com.amalitech.movaapp.data.remote.dto.SpokenLanguageDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PLog {
    companion object {
        fun v(msg: String) {
            Log.v(LOG_TAG, msg)
        }
        fun e(msg: String) {
            Log.e(LOG_TAG, msg)
        }

        const val LOG_TAG = "MovaApp"
    }
}
fun getImageUrl(path: String): String {
    return IMAGE_BASE_URL + path
}

fun getVideoUrl(site: String, key: String): String {
    return when(site) {
        VideoType.YouTube.name -> "https://www.youtube.com/watch?v=$key"
        VideoType.Vimeo.name -> "https://vimeo.com/$key"
        else -> ""
    }
}

fun genreString(list: List<GenreDto>?): String {
    return list?.joinToString(separator = ", ") { it.name } ?: ""
}

fun spokenLanguagesString(list: List<SpokenLanguageDto>?): String {
    return if (list?.isNotEmpty() == true) {
        list?.get(0)?.english_name ?: ""
    } else {
        ""
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateFromISOUTC(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val date = LocalDate.parse(dateString, inputFormatter)
    return outputFormatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateFromSimpleFormat(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val date = LocalDate.parse(dateString, inputFormatter)
    return outputFormatter.format(date)
}
