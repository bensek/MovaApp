package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.data.remote.dto.ApiData
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.repository.MoviesRepository
import com.amalitech.movaapp.ft_home.MovieType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class GetMoviesBasedOnTypeUseCaseImpl(
    private val repository: MoviesRepository,
    private val mapper: MovieMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): GetMoviesBasedOnTypeUseCase {

    override suspend fun invoke(movieType: MovieType): Flow<Resource<List<Movie>>> = withContext(dispatcher) {
        flow {
            try {
                emit(Resource.Loading())
                val apiResponse = when(movieType) {
                    is MovieType.Popular -> repository.fetchPopularMovies()
                    is MovieType.TopRated -> repository.fetchTopMovies()
                    is MovieType.Upcoming -> repository.fetchUpcomingMovies()
                    else -> throw IllegalArgumentException("Invalid movie type")
                }
                if (apiResponse.isSuccessful) {
                    val data = apiResponse.body() as ApiData
                    emit(Resource.Success(mapper(data.results)))
                } else {
                    emit(Resource.Error("Api is unsuccessful"))
                }
            } catch (e: IOException) {
                emit(Resource.Error("IO Exception: ${e.message}"))
            } catch (e: HttpException) {
                emit(Resource.Error("Http Exception: ${e.message}"))
            } catch (e: TimeoutException) {
                emit(Resource.Error("Timeout Exception: ${e.message}"))
            }
        }
    }
}