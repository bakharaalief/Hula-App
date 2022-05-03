package com.bakharaalief.hulaapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val getFavoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}