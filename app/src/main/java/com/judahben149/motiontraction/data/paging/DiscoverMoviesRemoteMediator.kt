package com.judahben149.motiontraction.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.judahben149.motiontraction.data.local.MovieDatabase
import com.judahben149.motiontraction.data.local.entity.MovieEntityRemoteKey
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.remote.MovieService
import com.judahben149.motiontraction.domain.mappers.toMovieListDBEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class DiscoverMoviesRemoteMediator(
    private val database: MovieDatabase,
    private val moviesService: MovieService,
    private val initialPage: Int = 1
) : RemoteMediator<Int, MovieListEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieListEntity>
    ): MediatorResult {

        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {


            val response = moviesService.fetchDiscoverMoviesList(page)
//            val endOfPagination = response.body()?.data?.size!! < state.config.pageSize
            val endOfPagination =
                response.body()?.data?.size!! < state.config.pageSize || response.body()?.data?.isEmpty() == true


            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.movieDao.deleteAllArticles()
                    database.remoteKeyDao.deleteAllRemoteKeys()
                }

                val prevKey = if (page == initialPage) null else page.minus(1)
                val nextKey = if (endOfPagination) null else page.plus(1)

                val remoteKeyList = response.body()?.data?.map { moviesDto ->
                    MovieEntityRemoteKey(moviesDto.id, prevKey, nextKey)
                }
                val movieEntityList = response.body()?.data?.map {
                    it.toMovieListDBEntity()
                }

                remoteKeyList?.let { keysList ->
                    database.remoteKeyDao.insertAllRemoteKeys(keysList)
                }

                if (movieEntityList != null) {
                    database.movieDao.insertMovies(movieEntityList)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, MovieListEntity>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.next?.minus(1) ?: 1
            }

            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.next
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }

            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prev ?: return MediatorResult.Success(endOfPaginationReached = false)
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id?.let { movieId ->
                database.remoteKeyDao.getAllRemoteKeys(movieId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            database.remoteKeyDao.getAllRemoteKeys(movie.id)
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieListEntity>): MovieEntityRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            database.remoteKeyDao.getAllRemoteKeys(movie.id)
        }
    }
}