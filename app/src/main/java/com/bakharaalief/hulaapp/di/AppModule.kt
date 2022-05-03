package com.bakharaalief.hulaapp.di

import com.bakharaalief.hulaapp.core.domain.usecase.MovieInteract
import com.bakharaalief.hulaapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieUseCase(movieInteract: MovieInteract): MovieUseCase
}