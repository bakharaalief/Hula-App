package com.bakharaalief.hulaapp.di

import com.bakharaalief.huluapp.core.domain.usecase.MovieInteract
import com.bakharaalief.huluapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteract: MovieInteract): MovieUseCase
}