package com.bakharaalief.hulaapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.model.Movie
import com.bakharaalief.huluapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movies: LiveData<Resource<List<Movie>>> = movieUseCase.getAllMovies().asLiveData()
}