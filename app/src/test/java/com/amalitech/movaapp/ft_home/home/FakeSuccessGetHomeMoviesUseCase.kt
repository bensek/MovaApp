package com.amalitech.movaapp.ft_home.home

import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.use_case.GetHomeMoviesUseCase
import com.amalitech.movaapp.util.generateDummyMovies
import com.amalitech.movaapp.util.generateSingleMovie

class FakeSuccessGetHomeMoviesUseCase: GetHomeMoviesUseCase {
    override suspend fun invoke(): Result<HomeMovies> {
        return Result.success(
            HomeMovies(
                popular = generateDummyMovies(),
                topRated = generateDummyMovies(),
                upcoming = generateDummyMovies(),
                featured = generateSingleMovie()
            )
        )
    }
}