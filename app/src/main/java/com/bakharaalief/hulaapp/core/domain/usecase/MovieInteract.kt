package com.bakharaalief.hulaapp.core.domain.usecase

import com.bakharaalief.hulaapp.core.data.Resource
import com.bakharaalief.hulaapp.core.domain.model.Movie
import com.bakharaalief.hulaapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteract(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}