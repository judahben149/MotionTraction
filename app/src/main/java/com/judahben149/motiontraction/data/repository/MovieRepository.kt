package com.judahben149.motiontraction.data.repository

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesListData>>

    fun fetchDiscoverMovieListCached(): Flow<PagingData<MovieListEntity>>

    suspend fun getMovieDetails(id: Int): Response<MovieDetailDto>

}