package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.local.entity.MovieDetailEntity
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListData
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListDto
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.domain.models.ListMovie


//to remove
fun DiscoverMoviesListData.toListMovie(): ListMovie {
    return ListMovie(
        id = this.id,
        backdropPath = this.backdrop_path,
        title = this.title,
        releaseDate = this.release_date,
        posterPath = this.poster_path
    )
}

fun MovieListEntity.toListMovie(): ListMovie {
    return ListMovie(
        id = this.id,
        backdropPath = this.backdrop_path,
        title = this.title,
        releaseDate = this.release_date,
        posterPath = this.poster_path
    )
}

fun DiscoverMoviesListData.toMovieListDBEntity(): MovieListEntity {
    return MovieListEntity(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun DiscoverMoviesListDto.toMovieResponseEntity(): MovieResponseEntity {
    return MovieResponseEntity (
        page = this.page,
        movieListEntity = this.data.map { it.toMovieListDBEntity() },
        totalPages = this.total_pages,
        totalResults = this.total_results
    )
}

//to remove
fun toMovieModel(movieDto: MovieDetailDto): DetailMovie {
    return DetailMovie(
        adult = movieDto.adult,
        backdropPath = movieDto.backdrop_path ?: "",
        budget = movieDto.budget.toDouble(),
        genres = movieDto.genres,
        homepageUrl = movieDto.homepage ?: "",
        id = movieDto.id,
        imdbId = movieDto.imdb_id ?: "",
        originalLanguage = movieDto.original_language,
        originalTitle = movieDto.original_title,
        overview = movieDto.overview ?: "",
        popularity = movieDto.popularity,
        posterPath = movieDto.poster_path ?: "",
        releaseDate = movieDto.release_date,
        revenue = movieDto.revenue.toDouble(),
        runtime = movieDto.runtime ?: 0,
        spokenLanguages = movieDto.spoken_languages,
        status = movieDto.status,
        tagline = movieDto.tagline ?: "",
        title = movieDto.title,
        video = movieDto.video,
        voteAverage = movieDto.vote_average,
        voteCount = movieDto.vote_count
    )
}

fun toMovieDBEntity(movieDto: MovieDetailDto): MovieDetailEntity {
    return MovieDetailEntity(
        id = movieDto.id,
        adult = movieDto.adult,
        backdrop_path = movieDto.backdrop_path ?: "",
        budget = movieDto.budget.toDouble(),
        homepageUrl = movieDto.homepage ?: "",
        imdb_id = movieDto.imdb_id ?: "",
        original_language = movieDto.original_language,
        original_title = movieDto.original_title,
        overview = movieDto.overview ?: "",
        popularity = movieDto.popularity,
        poster_path = movieDto.poster_path ?: "",
        release_date = movieDto.release_date,
        revenue = movieDto.revenue.toDouble(),
        runtime = movieDto.runtime ?: 0,
        status = movieDto.status,
        tagline = movieDto.tagline ?: "",
        title = movieDto.title,
        video = movieDto.video,
        vote_average = movieDto.vote_average,
        vote_count = movieDto.vote_count
    )
}
