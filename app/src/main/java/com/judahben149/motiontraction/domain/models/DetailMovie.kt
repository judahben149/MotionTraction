package com.judahben149.motiontraction.domain.models

import com.judahben149.motiontraction.data.remote.dto.movieDetail.GenreX
import com.judahben149.motiontraction.data.remote.dto.movieDetail.SpokenLanguage

data class DetailMovie(

    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Double = 0.0,
    val genres: List<GenreX>,
    val homepageUrl: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val revenue: Double = 0.0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)