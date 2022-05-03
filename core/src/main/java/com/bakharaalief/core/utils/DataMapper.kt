package com.bakharaalief.core.utils

import com.bakharaalief.core.data.source.local.entity.MovieEntity
import com.bakharaalief.core.data.source.remote.response.MovieItem
import com.bakharaalief.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieItem>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.voteAverage,
                it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        input.id,
        input.originalLanguage,
        input.originalTitle,
        input.overview,
        input.popularity,
        input.posterPath,
        input.releaseDate,
        input.voteAverage,
        input.isFavorite
    )
}