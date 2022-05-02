package com.bakharaalief.hulaapp.main

import androidx.lifecycle.ViewModel
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies()
}