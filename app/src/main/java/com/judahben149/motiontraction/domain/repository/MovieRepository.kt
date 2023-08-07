package com.judahben149.motiontraction.domain.repository

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.source.local.entity.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.source.remote.dto.credits.CreditsDto
import io.reactivex.Flowable
import io.reactivex.Observable

interface MovieRepository {

    fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>>

    fun getMovieDetail(id: Int): Observable<MovieDetailEntity>

    fun getMovieCredits(id: Int): Observable<CreditsDto>

}