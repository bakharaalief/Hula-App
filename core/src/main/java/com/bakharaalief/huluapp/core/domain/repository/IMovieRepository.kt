package com.bakharaalief.huluapp.core.domain.repository

import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}