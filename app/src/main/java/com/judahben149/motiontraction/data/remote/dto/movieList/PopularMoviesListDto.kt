package com.judahben149.motiontraction.data.remote.dto.movieList

import com.google.gson.annotations.SerializedName

data class PopularMoviesListDto(
    val page: Int,
    @SerializedName("results")
    val `data`: List<PopularMoviesListData>,
    val total_pages: Int
) {
    data class PopularMoviesListData(
        val id: Long,
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