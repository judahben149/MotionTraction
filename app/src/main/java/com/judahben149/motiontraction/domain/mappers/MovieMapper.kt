package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.local.entity.MovieDetailEntity
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListData
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.domain.models.ListMovie


//to remove
fun dtoToMovieListModel(discoverMoviesResponse: DiscoverMoviesListData): ListMovie {
    return ListMovie(
        id = discoverMoviesResponse.id,
        backdropPath = discoverMoviesResponse.backdrop_path,
        title = discoverMoviesResponse.title,
        releaseDate = discoverMoviesResponse.release_date,
        posterPath = discoverMoviesResponse.poster_path
    )
}

fun entityToMovieListModel(movieListEntity: MovieListEntity): ListMovie {
    return ListMovie(
        id = movieListEntity.id,
        backdropPath = movieListEntity.backdrop_path,
        title = movieListEntity.title,
        releaseDate = movieListEntity.release_date,
        posterPath = movieListEntity.poster_path
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
