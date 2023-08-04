package com.judahben149.motiontraction.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.judahben149.motiontraction.data.local.MovieDatabase
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.paging.DiscoverMoviesPagingDataSource
import com.judahben149.motiontraction.data.paging.DiscoverMoviesRemoteMediator
import com.judahben149.motiontraction.data.remote.MovieService
import com.judahben149.motiontraction.data.remote.dto.movieDetail.MovieDetailDto
import com.judahben149.motiontraction.data.remote.dto.movieList.DiscoverMoviesListData
import com.judahben149.motiontraction.utils.Constants.NETWORK_PAGE_SIZE
import com.judahben149.motiontraction.utils.Constants.STARTING_PAGE_INDEX
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MovieService,
    private val database: MovieDatabase
) : MovieRepository {

    override fun fetchDiscoverMovieList(): Flow<PagingData<DiscoverMoviesListData>> {

        return Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 5
            )
        ) {
            DiscoverMoviesPagingDataSource(moviesService)
        }.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchDiscoverMovieListCached(): Flow<PagingData<MovieListEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 5
            ),
            remoteMediator = DiscoverMoviesRemoteMediator(database, moviesService, STARTING_PAGE_INDEX),
            pagingSourceFactory = { database.movieDao.getAllMovies() }
        ).flow
    }

    @WorkerThread
    override suspend fun getMovieDetails(id: Int): Response<MovieDetailDto> {
        return moviesService.fetchMovieDetails(id)
    }
}