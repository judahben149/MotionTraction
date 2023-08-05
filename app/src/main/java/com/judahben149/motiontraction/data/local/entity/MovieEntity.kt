package com.judahben149.motiontraction.data.local.entity

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
        val backdrop_path: String,
        val original_language: String,
        val original_title: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    )
}