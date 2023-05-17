package com.amalitech.movaapp.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.amalitech.movaapp.core.util.formatDateFromISOUTC
import com.amalitech.movaapp.core.util.getVideoUrl
import com.amalitech.movaapp.domain.model.Video
import com.google.gson.annotations.SerializedName

data class VideoDto(
    val id: String,
    val name: String,
    val site: String,
    val key: String,
    val type: String,
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun VideoDto.toVideo(): Video {
    return Video(
        name = name,
        official = official,
        site = site,
        url = getVideoUrl(site, key),
        date = formatDateFromISOUTC(publishedAt)
    )
}

