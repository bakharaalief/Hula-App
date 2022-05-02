package com.bakharaalief.hulaapp.core.di

import android.content.Context
import com.bakharaalief.hulaapp.core.data.MovieRepository
import com.bakharaalief.hulaapp.core.data.source.local.LocalDataSource
import com.bakharaalief.hulaapp.core.data.source.local.room.MovieDB
import com.bakharaalief.hulaapp.core.data.source.remote.RemoteDataSource
import com.bakharaalief.hulaapp.core.data.source.remote.retorift.ApiConfig
import com.bakharaalief.hulaapp.core.domain.repository.IMovieRepository
import com.bakharaalief.hulaapp.core.domain.usecase.MovieInteract
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase
import com.bakharaalief.hulaapp.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IMovieRepository {
        val apiService = ApiConfig.provideApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiService)

        val movieDao = MovieDB.getInstance(context).movieDao()
        val localDataSource = LocalDataSource.getInstance(movieDao)

        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val movieRepository = provideRepository(context)
        return MovieInteract(movieRepository)
    }
}