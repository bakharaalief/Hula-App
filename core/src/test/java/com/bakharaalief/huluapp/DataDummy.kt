package com.bakharaalief.huluapp

import com.bakharaalief.huluapp.core.domain.model.Movie

object DataDummy {
    fun generateDummyMovies(): List<Movie> {
        val movieList = ArrayList<Movie>()
        for (i in 0..10) {
            val movie = Movie(
                id = i,
                originalLanguage = "en",
                originalTitle = "Movie $i",
                overview = "Overview $i",
                popularity = 0.0,
                posterPath = "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/commons/feature-1-kurikulum-global-3.png",
                releaseDate = "04-12-99",
                voteAverage = 0.0,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
}