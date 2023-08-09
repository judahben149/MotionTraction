package com.judahben149.motiontraction.data.source.local.entity.movieList

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MovieResponseEntity(
    val page: Int,
    val movieEntity: List<MovieEntity>,
    val totalPages: Int
) {
    val isEndOfPage = page == totalPages

    @Entity(tableName = "movies")
    data class MovieEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val movieId: Long,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val popularity: Double,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int,
        val isFavorite: Boolean
    )
}