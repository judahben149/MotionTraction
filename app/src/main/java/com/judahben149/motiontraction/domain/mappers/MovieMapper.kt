package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.source.local.entity.movieDetail.GenreEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCompanyEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCountryEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.SpokenLanguageEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.GenreDto
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.ProductionCompanyDto
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.ProductionCountryDto
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.SpokenLanguageDto
import com.judahben149.motiontraction.data.source.remote.dto.movieList.PopularMoviesListDto
import com.judahben149.motiontraction.domain.models.movieDetail.DetailMovie
import com.judahben149.motiontraction.domain.models.movieDetail.Genre
import com.judahben149.motiontraction.domain.models.movieDetail.ProductionCompany
import com.judahben149.motiontraction.domain.models.movieDetail.ProductionCountry
import com.judahben149.motiontraction.domain.models.movieDetail.SpokenLanguage
import com.judahben149.motiontraction.domain.models.movieList.ListMovie


fun MovieResponseEntity.MovieEntity.toListMovie(): ListMovie {
    return ListMovie(
        id = this.movieId,
        backdropPath = this.backdropPath,
        title = this.title,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath
    )
}


fun PopularMoviesListDto.toMovieResponseEntity(): MovieResponseEntity {
    return MovieResponseEntity(
        page = this.page,
        movieEntity = this.data.map { it.toMovieResponseDataEntity() },
        totalPages = this.totalPages
    )
}

fun PopularMoviesListDto.PopularMoviesListData.toMovieResponseDataEntity(): MovieResponseEntity.MovieEntity {
    return MovieResponseEntity.MovieEntity(
        movieId = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath ?: "",
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        popularity = this.popularity,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovieDetailEntity.toDetailMovie(): DetailMovie {
    return DetailMovie(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        budget = this.budget,
        homepageUrl = this.homepageUrl,
        genres = this.genres.map { it.toGenre() },
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { it.toProductionCompany() },
        productionCountries = this.productionCountries.map { it.toProductionCountry() },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.toSpokenLanguage() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovieDetailDto.toMovieDetailEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath ?: "",
        budget = this.budget.toDouble(),
        genres = this.genres.map { it.toGenreEntity() },
        homepageUrl = this.homepage ?: "",
        imdbId = this.imdbId ?: "",
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview ?: "",
        popularity = this.popularity,
        posterPath = this.posterPath ?: "",
        productionCompanies = this.productionCompanies?.map { it.toProductionCompanyEntity() } ?: emptyList(),
        productionCountries = this.productionCountries?.map { it.toProductionCountryEntity() } ?: emptyList(),
        releaseDate = this.releaseDate,
        revenue = this.revenue.toDouble(),
        runtime = this.runtime ?: 0,
        spokenLanguages = this.spokenLanguages?.map { it.toSpokenLanguageEntity() } ?: emptyList(),
        status = this.status,
        tagline = this.tagline ?: "",
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun ProductionCompanyDto.toProductionCompanyEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        id = this.id ?: 0,
        logoPath = this.logoPath  ?: "",
        name = this.name ?: "",
        originCountry = this.originCountry ?: ""
    )
}

fun ProductionCountryDto.toProductionCountryEntity(): ProductionCountryEntity {
    return ProductionCountryEntity(
        iso6391 = this.iso6391 ?: "",
        name = this.name ?: ""
    )
}

fun SpokenLanguageDto.toSpokenLanguageEntity(): SpokenLanguageEntity {
    return SpokenLanguageEntity(
        englishName = this.englishName ?: "",
        iso6391 = this.iso6391 ?: "",
        name = this.name ?: ""
    )
}


fun GenreDto.toGenreEntity(): GenreEntity {
    return GenreEntity(
        id = this.id ?: 0,
        name = this.name ?: ""
    )
}

fun ProductionCompanyEntity.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = this.id,
        logoPath = this.logoPath,
        name = this.name,
        originCountry = this.originCountry
    )
}

fun ProductionCountryEntity.toProductionCountry(): ProductionCountry {
    return ProductionCountry(
        iso6391 = this.iso6391,
        name = this.name
    )
}

fun SpokenLanguageEntity.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = this.englishName,
        iso6391 = this.iso6391,
        name = this.name
    )
}


fun GenreEntity.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}