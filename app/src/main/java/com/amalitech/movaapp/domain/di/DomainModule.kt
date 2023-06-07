package com.amalitech.movaapp.domain.di

import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.domain.repository.MoviesRepository
import com.amalitech.movaapp.domain.use_case.GetHomeMoviesUseCase
import com.amalitech.movaapp.domain.use_case.GetHomeMoviesUseCaseImpl
import com.amalitech.movaapp.domain.use_case.GetMoviesBasedOnTypeUseCase
import com.amalitech.movaapp.domain.use_case.GetMoviesBasedOnTypeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun providesGetPopularMoviesUseCase(
        repository: MoviesRepository
    ): GetMoviesBasedOnTypeUseCase {
        return GetMoviesBasedOnTypeUseCaseImpl(repository)
    }

    @Provides
    fun providesGetHomeMoviesUseCase(
        repository: MoviesRepository
    ): GetHomeMoviesUseCase {
        return GetHomeMoviesUseCaseImpl(repository)
    }


}