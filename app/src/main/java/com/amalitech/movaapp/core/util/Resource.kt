package com.amalitech.movaapp.core.util

sealed class Resource<T>(
    val data: T? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(exception: Throwable): Resource<T>(exception = exception)
}






