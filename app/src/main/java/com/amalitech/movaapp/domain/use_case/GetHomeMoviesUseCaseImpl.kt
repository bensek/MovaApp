package com.amalitech.movaapp.domain.use_case

import com.amalitech.movaapp.core.util.Resource
import com.amalitech.movaapp.data.remote.dto.MovieMapper
import com.amalitech.movaapp.domain.model.HomeMovies
import com.amalitech.movaapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

class GetHomeMoviesUseCaseImpl(
    private val repository: MoviesRepository,
    private val mapper: MovieMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): GetHomeMoviesUseCase {
    override suspend fun invoke(): Flow<Resource<HomeMovies>> = withContext(dispatcher){
        flow {
            emit(Resource.Loading())
            try {
                val popularDeferred = async { repository.fetchPopularMovies() }
                val topRatedDeferred = async { repository.fetchTopMovies() }
                val upcomingDeferred = async { repository.fetchUpcomingMovies() }
                val featuredDeferred = async { repository.fetchNowPlayingMovies() }

                val popular = popularDeferred.await()
                val topRated = topRatedDeferred.await()
                val upcoming = upcomingDeferred.await()
                val featured = featuredDeferred.await()

                if (popular.isSuccessful && topRated.isSuccessful && upcoming.isSuccessful && featured.isSuccessful) {
                    emit(Resource.Success(
                        HomeMovies(
                            popularMovies = mapper(popular.body()?.results!!),
                            topRatedMovies = mapper(topRated.body()?.results!!),
                            upcomingMovies = mapper(upcoming.body()?.results!!),
                            featuredMovie = mapper(featured.body()?.results!!)[0]
                        )
                    ))
                } else {
                    emit(Resource.Error("Api failed to fetch movies"))
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