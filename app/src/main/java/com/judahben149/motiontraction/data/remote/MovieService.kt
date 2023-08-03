package com.judahben149.motiontraction.data.remote

import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMoviesList(@Query("page") pageNumber: Int): Response<DiscoverMoviesListDto>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") id: Int) : Response<MovieDetailDto>
}