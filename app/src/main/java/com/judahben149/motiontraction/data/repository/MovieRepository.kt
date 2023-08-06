package com.judahben149.motiontraction.data.repository

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.remote.dto.credits.CreditsDto
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface MovieRepository {

    fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>>

    fun getMovieDetail(id: Int): Observable<MovieDetailDto>
    fun getMovieCredits(id: Int): Observable<CreditsDto>

}