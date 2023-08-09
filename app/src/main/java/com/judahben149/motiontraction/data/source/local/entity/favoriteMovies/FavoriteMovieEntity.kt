package com.judahben149.motiontraction.data.source.local.entity.favoriteMovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(

    @PrimaryKey(autoGenerate = false)
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