package com.bakharaalief.huluapp.core.domain.usecase

import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.model.Movie
import com.bakharaalief.huluapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteract @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}