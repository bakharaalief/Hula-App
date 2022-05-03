package com.bakharaalief.hulaapp.core.data.source.local

import com.bakharaalief.hulaapp.core.data.source.local.entity.MovieEntity
import com.bakharaalief.hulaapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllTourism()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteTourism()

    suspend fun insertMovies(moviesList: List<MovieEntity>) = movieDao.insertTourism(moviesList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteTourism(movie)
    }
}