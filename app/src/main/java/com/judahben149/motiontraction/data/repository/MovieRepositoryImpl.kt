package com.judahben149.motiontraction.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.judahben149.motiontraction.data.local.MovieDatabase
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.paging.DiscoverMoviesRXRemoteMediator
import com.judahben149.motiontraction.data.remote.MovieService
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.utils.Constants.INITIAL_LOAD_SIZE
import com.judahben149.motiontraction.utils.Constants.MAX_LOAD_SIZE
import com.judahben149.motiontraction.utils.Constants.NETWORK_PAGE_SIZE
import com.judahben149.motiontraction.utils.Constants.PRE_FETCH_DISTANCE
import com.judahben149.motiontraction.utils.Constants.STARTING_PAGE_INDEX
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MovieService,
    private val database: MovieDatabase
) : MovieRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                maxSize = MAX_LOAD_SIZE,
                prefetchDistance = PRE_FETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE
            ),
            remoteMediator = DiscoverMoviesRXRemoteMediator(database, moviesService, STARTING_PAGE_INDEX),
            pagingSourceFactory = { database.movieDao.getAllMovies() }
        ).flowable
    }


    @WorkerThread
    override suspend fun getMovieDetails(id: Int): Response<MovieDetailDto> {
        return moviesService.fetchMovieDetails(id)
    }
}