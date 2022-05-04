package com.bakharaalief.huluapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bakharaalief.huluapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel (movieUseCase: MovieUseCase) : ViewModel() {
    val getFavoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}