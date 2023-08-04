package com.judahben149.motiontraction.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.judahben149.motiontraction.data.local.MovieDatabase
import com.judahben149.motiontraction.data.local.entity.MovieEntityRemoteKey
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.remote.MovieService
import com.judahben149.motiontraction.domain.mappers.toMovieResponseEntity
import com.judahben149.motiontraction.utils.Constants.INVALID_PAGE
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class DiscoverMoviesRXRemoteMediator(
    private val database: MovieDatabase,
    private val moviesService: MovieService,
    private val initialPage: Int = 1
) : RxRemoteMediator<Int, MovieListEntity>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, MovieListEntity>
    ): Single<MediatorResult> {

        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when(it) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.next?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getFirstRemoteKey(state) ?: throw InvalidObjectException("Empty result")
                        remoteKeys.prev ?: INVALID_PAGE
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getLastRemoteKey(state) ?: throw InvalidObjectException("Empty result")
                        remoteKeys.next ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    moviesService.fetchDiscoverMoviesList(page)
                        .map { it.toMovieResponseEntity() }
                        .map { insertIntoDatabase(page, loadType, it) }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = it.isEndOfPage) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }
            }
            .onErrorReturn { MediatorResult.Error(it) }
    }

    private fun insertIntoDatabase(page: Int, loadType: LoadType, data: MovieResponseEntity): MovieResponseEntity {
        database.beginTransaction()

        try {
            if (loadType == LoadType.REFRESH) {
                database.movieDao.deleteAllArticles()
                database.remoteKeyDao.deleteAllRemoteKeys()
            }

            val prevKey = if (page == initialPage) null else page.minus(1)
            val nextKey = if (data.isEndOfPage) null else page.plus(1)

            val remoteKeyList = data.movieListEntity.map { movie ->
                MovieEntityRemoteKey(movie.id, prevKey, nextKey)
            }

            database.remoteKeyDao.insertAllRemoteKeys(remoteKeyList)
            database.movieDao.insertMovies(data.movieListEntity)
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }

        return data
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id?.let { movieId ->
                database.remoteKeyDao.getAllRemoteKeys(movieId)
            }
        }
    }

    private fun getLastRemoteKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            database.remoteKeyDao.getAllRemoteKeys(movie.id)
        }
    }

    private fun getFirstRemoteKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            database.remoteKeyDao.getAllRemoteKeys(movie.id)
        }
    }
}