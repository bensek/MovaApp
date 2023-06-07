package com.amalitech.movaapp.data.remote.retrofit

sealed interface ApiResult<T: Any> {
    class Success<T: Any>(val data: Any?) : ApiResult<T>
    class Error<T: Any>(val code: Int, val message: String?) : ApiResult<T>
    class Exception<T: Any>(val exception: Throwable) : ApiResult<T>
}
