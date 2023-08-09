package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.domain.models.favoriteMovies.FavoriteMovie

fun FavoriteMovieEntity.toFavoriteMovie(): FavoriteMovie {
    return FavoriteMovie(
        movieId = this.movieId,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalTitle = this.originalTitle,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun FavoriteMovie.toFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        movieId = this.movieId,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalTitle = this.originalTitle,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}