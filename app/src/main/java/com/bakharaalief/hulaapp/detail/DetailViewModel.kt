package com.bakharaalief.hulaapp.detail

import androidx.lifecycle.ViewModel
import com.bakharaalief.hulaapp.core.domain.model.Movie
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setMovieFavorite(movie: Movie, state: Boolean) = movieUseCase.setFavoriteMovie(movie, state)
}