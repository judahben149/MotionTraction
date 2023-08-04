package com.judahben149.motiontraction.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)


data class MovieResponseEntity(
    val page: Int,
    val movieListEntity: List<MovieListEntity>,
    val totalPages: Int,
    val totalResults: Int
) {
    val isEndOfPage = page == totalPages
}