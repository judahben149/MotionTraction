package com.judahben149.motiontraction.data.repository

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun getMovieList(): Flowable<PagingData<MovieListEntity>>

    suspend fun getMovieDetails(id: Int): Response<MovieDetailDto>

}