package com.judahben149.motiontraction.data.source.remote

import com.judahben149.motiontraction.data.source.remote.dto.credits.CreditsDto
import com.judahben149.motiontraction.data.source.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.source.remote.dto.movieList.PopularMoviesListDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("movie/popular")
    fun fetchDiscoverMoviesList(@Query("page") pageNumber: Int): Single<PopularMoviesListDto>

    @GET("movie/{movie_id}")
    fun fetchMovieDetail(@Path("movie_id") id: Int) : Single<Response<MovieDetailDto>>

    @GET("movie/{movie_id}/credits")
    fun fetchMovieCredits(@Path("movie_id") id: Int) : Observable<CreditsDto>
}