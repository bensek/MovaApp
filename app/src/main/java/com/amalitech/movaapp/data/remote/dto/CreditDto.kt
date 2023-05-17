package com.amalitech.movaapp.data.remote.dto

import com.amalitech.movaapp.core.util.Constants.IMAGE_BASE_URL
import com.amalitech.movaapp.domain.model.Credit
import com.google.gson.annotations.SerializedName

data class CreditDto(
    val id: Int,
    val name: String,
    val gender: Int,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val order: Int
)

fun CreditDto.toCredit(): Credit {
    return Credit(
        id = id,
        name = name,
        role = knownForDepartment,
        imageUrl = IMAGE_BASE_URL + profilePath
    )
}
