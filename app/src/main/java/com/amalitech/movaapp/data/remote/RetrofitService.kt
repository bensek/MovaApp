//package com.amalitech.movaapp.data.remote
//
//import okhttp3.*
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//class RetrofitService {
//
//    companion object {
//
//
//        private val client = OkHttpClient.Builder()
//            .addInterceptor(ApiKeyInterceptor(API_KEY))
//            .build()
//
//        val apiInterface: ApiInterface = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//            .create(ApiInterface::class.java)
//    }
//}
//
