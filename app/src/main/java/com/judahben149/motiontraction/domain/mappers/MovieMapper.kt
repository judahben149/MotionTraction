package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.remote.dto.movieList.PopularMoviesListDto
import com.judahben149.motiontraction.domain.models.ListMovie


fun MovieResponseEntity.MovieEntity.toListMovie(): ListMovie {
    return ListMovie(
        id = this.id,
        backdropPath = this.backdrop_path,
        title = this.title,
        releaseDate = this.release_date,
        posterPath = this.poster_path
    )
}


fun PopularMoviesListDto.toMovieResponseEntity(): MovieResponseEntity {
    return MovieResponseEntity(
        page = this.page,
        movieEntity = this.data.map { it.toMovieResponseDataEntity() },
        totalPages = this.total_pages
    )
}

fun PopularMoviesListDto.PopularMoviesListData.toMovieResponseDataEntity(): MovieResponseEntity.MovieEntity {
    return MovieResponseEntity.MovieEntity(
        movieId = this.id,
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        original_language = this.original_language,
        original_title = this.original_title,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}