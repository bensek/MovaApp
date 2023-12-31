package com.amalitech.movaapp.data.di

import com.amalitech.movaapp.core.util.Constants
import com.amalitech.movaapp.core.util.Constants.BASE_URL
import com.amalitech.movaapp.data.remote.ApiInterface
import com.amalitech.movaapp.data.remote.ApiKeyInterceptor
import com.amalitech.movaapp.data.remote.retrofit.ApiResultCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesOkHttpClient(interceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}