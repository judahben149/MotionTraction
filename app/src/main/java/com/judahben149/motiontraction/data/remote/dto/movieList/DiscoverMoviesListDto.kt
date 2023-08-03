package com.judahben149.motiontraction.data.remote.dto.movieList

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesListDto(
    val page: Int,
    @SerializedName("results")
    val `data`: List<DiscoverMoviesListData>,
    val total_pages: Int,
    val total_results: Int
)