package com.judahben149.motiontraction.data.source.remote.dto.movieList

import com.google.gson.annotations.SerializedName

data class PopularMoviesListDto(
    val page: Int,

    @SerializedName("results")
    val `data`: List<PopularMoviesListData>,

    @SerializedName("total_pages")
    val totalPages: Int,
) {

    data class PopularMoviesListData(
        val id: Long,

        val adult: Boolean,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("original_language")
        val originalLanguage: String,

        @SerializedName("original_title")
        val originalTitle: String,

        @SerializedName("poster_path")
        val posterPath: String?,

        val popularity: Double,

        @SerializedName("release_date")
        val releaseDate: String,

        val title: String,

        val video: Boolean,

        @SerializedName("vote_average")
        val voteAverage: Double,

        @SerializedName("vote_count")
        val voteCount: Int,
    )
}