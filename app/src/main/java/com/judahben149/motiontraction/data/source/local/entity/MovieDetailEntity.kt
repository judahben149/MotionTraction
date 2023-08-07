package com.judahben149.motiontraction.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDetailEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Double,
    val homepageUrl: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Double,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
