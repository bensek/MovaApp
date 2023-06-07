package com.amalitech.movaapp.data.di

import com.amalitech.movaapp.data.remote.ApiInterface
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.data.repository.MoviesRepositoryImpl
import com.amalitech.movaapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun providesMoviesRepository(api: ApiInterface, mapper: MovieMapper): MoviesRepository {
        return MoviesRepositoryImpl(api, mapper)
    }
}