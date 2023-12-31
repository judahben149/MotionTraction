package com.judahben149.motiontraction.domain.models.movieList

data class ListMovie(
    val id: Long = 0L,
    val backdropPath: String = "",
    val title: String = "",
    val releaseDate: String = "",
    val posterPath: String = "",
    val isFavorite: Boolean = false
)
