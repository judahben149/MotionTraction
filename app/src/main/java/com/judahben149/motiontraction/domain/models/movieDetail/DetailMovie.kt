package com.judahben149.motiontraction.domain.models.movieDetail

data class DetailMovie(

    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Double = 0.0,
    val genres: List<Genre> = emptyList(),
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
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val isFavorite: Boolean = false
)

data class ProductionCompany(
    val id: Int =0,
    val logoPath: String = "",
    val name: String = "",
    val originCountry: String = ""
)

data class ProductionCountry(
    val iso6391: String = "",
    val name: String = ""
)

data class SpokenLanguage(
    val englishName: String = "",
    val iso6391: String = "",
    val name: String = ""
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

