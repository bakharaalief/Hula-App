package com.bakharaalief.hulaapp.core.data.source.local

import com.bakharaalief.hulaapp.core.data.source.local.entity.MovieEntity
import com.bakharaalief.hulaapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllTourism()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteTourism()

    suspend fun insertMovies(moviesList: List<MovieEntity>) = movieDao.insertTourism(moviesList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteTourism(movie)
    }

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }
}