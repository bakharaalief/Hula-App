package com.bakharaalief.hulaapp.core.data

import com.bakharaalief.hulaapp.BuildConfig
import com.bakharaalief.hulaapp.core.data.source.local.LocalDataSource
import com.bakharaalief.hulaapp.core.data.source.remote.ApiResponse
import com.bakharaalief.hulaapp.core.data.source.remote.RemoteDataSource
import com.bakharaalief.hulaapp.core.data.source.remote.response.MovieItem
import com.bakharaalief.hulaapp.core.domain.model.Movie
import com.bakharaalief.hulaapp.core.domain.repository.IMovieRepository
import com.bakharaalief.hulaapp.core.utils.AppExecutors
import com.bakharaalief.hulaapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMovieRepository {

    val apiKey = BuildConfig.api_key

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getAllMovies().map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getAllTourism(apiKey)

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> = localDataSource.getFavoriteMovies().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors,
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }
}