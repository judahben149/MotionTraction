package com.judahben149.motiontraction.domain.models.favoriteMovies

data class FavoriteMovie(
    val movieId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
