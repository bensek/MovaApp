package com.amalitech.movaapp.data.remote.dto

data class ApiResponse(
    val page: Int,
    val results: List<MovieDto>
)
