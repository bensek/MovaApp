package com.amalitech.movaapp.data.remote.retrofit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResultCall<T: Any>(
    private val proxy: Call<T>
): Call<ApiResult<T>> {
    override fun enqueue(callback: Callback<ApiResult<T>>) {
        proxy.enqueue(object: Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Success(response.body())))
                } else {
                    callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Error(response.code(), response.message())))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Exception(t)))
            }
        })
    }

    override fun execute(): Response<ApiResult<T>> = throw NotImplementedError()
    override fun clone(): Call<ApiResult<T>> = ApiResultCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() { proxy.cancel() }

}