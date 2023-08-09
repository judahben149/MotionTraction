package com.judahben149.motiontraction.data.source.local.entity.movieDetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDetailEntity(

    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Double,
    val genres: List<GenreEntity>,
    val homepageUrl: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productionCountries: List<ProductionCountryEntity>,
    val releaseDate: String,
    val revenue: Double,
    val runtime: Int,
    val status: String,
    val spokenLanguages: List<SpokenLanguageEntity>,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean
)


data class ProductionCompanyEntity(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class ProductionCountryEntity(
    val iso6391: String,
    val name: String
)

data class SpokenLanguageEntity(
    val englishName: String,
    val iso6391: String,
    val name: String
)

data class GenreEntity(
    val id: Int,
    val name: String
)

data class MovieDetailEntityUpdate(
    val movieId: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Double,
    val genres: List<GenreEntity>,
    val homepageUrl: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productionCountries: List<ProductionCountryEntity>,
    val releaseDate: String,
    val revenue: Double,
    val runtime: Int,
    val status: String,
    val spokenLanguages: List<SpokenLanguageEntity>,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)
