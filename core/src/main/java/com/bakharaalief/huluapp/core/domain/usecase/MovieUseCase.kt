package com.bakharaalief.huluapp.core.domain.usecase

import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}