package com.judahben149.motiontraction.domain.repository

import androidx.paging.PagingData
import com.judahben149.motiontraction.utils.OperationResult
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import io.reactivex.Flowable
import io.reactivex.Observable

interface MovieRepository {

    fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>>

    fun getMovieDetail(id: Int): Observable<OperationResult<MovieDetailEntity>>

    fun getMovieCredits(id: Int): Observable<OperationResult<CreditsEntity>>

}