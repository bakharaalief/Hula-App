package com.bakharaalief.hulaapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val getFavoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}