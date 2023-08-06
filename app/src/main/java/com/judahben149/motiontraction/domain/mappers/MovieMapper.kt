package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.remote.dto.movieDetail.GenreDto
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieDetail.ProductionCompanyDto
import com.judahben149.motiontraction.data.remote.dto.movieDetail.ProductionCountryDto
import com.judahben149.motiontraction.data.remote.dto.movieDetail.SpokenLanguageDto
import com.judahben149.motiontraction.data.remote.dto.movieList.PopularMoviesListDto
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.domain.models.Genre
import com.judahben149.motiontraction.domain.models.ListMovie
import com.judahben149.motiontraction.domain.models.ProductionCompany
import com.judahben149.motiontraction.domain.models.ProductionCountry
import com.judahben149.motiontraction.domain.models.SpokenLanguage


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

fun MovieDetailDto.toDetailMovie(): DetailMovie {
    return DetailMovie(
        adult = this.adult,
        backdropPath = this.backdropPath ?: "",
        budget = this.budget.toDouble(),
        genres = this.genres.map { it.toGenre() },
        homepageUrl = this.homepage ?: "",
        id = this.id,
        imdbId = this.imdbId ?: "",
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview ?: "",
        popularity = this.popularity,
        posterPath = this.posterPath ?: "",
        productionCompanies = this.productionCompanies?.map { it.toProductionCompany() } ?: emptyList(),
        productionCountries = this.productionCountries?.map { it.toProductionCountry() }  ?: emptyList(),
        releaseDate = this.releaseDate,
        revenue = this.revenue.toDouble(),
        runtime = this.runtime ?: 0,
        spokenLanguages = this.spokenLanguages?.map { it.toSpokenLanguage() }  ?: emptyList(),
        status = this.status,
        tagline = this.tagline ?: "",
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun ProductionCompanyDto.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = this.id ?: 0,
        logoPath = this.logoPath  ?: "",
        name = this.name ?: "",
        originCountry = this.originCountry ?: ""
    )
}

fun ProductionCountryDto.toProductionCountry(): ProductionCountry {
    return ProductionCountry(
        iso6391 = this.iso6391 ?: "",
        name = this.name ?: ""
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = this.englishName ?: "",
        iso6391 = this.iso6391 ?: "",
        name = this.name ?: ""
    )
}


fun GenreDto.toGenre(): Genre {
    return Genre(
        id = this.id ?: 0,
        name = this.name ?: ""
    )
}