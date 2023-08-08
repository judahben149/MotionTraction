package com.judahben149.motiontraction.data.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.judahben149.motiontraction.data.source.local.MovieDatabase
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import com.judahben149.motiontraction.data.source.paging.PopularMoviesRXRemoteMediator
import com.judahben149.motiontraction.data.source.remote.MovieService
import com.judahben149.motiontraction.domain.mappers.toCreditsEntity
import com.judahben149.motiontraction.domain.mappers.toMovieDetailEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.utils.Constants.INITIAL_LOAD_SIZE
import com.judahben149.motiontraction.utils.Constants.MAX_LOAD_SIZE
import com.judahben149.motiontraction.utils.Constants.NETWORK_PAGE_SIZE
import com.judahben149.motiontraction.utils.Constants.PRE_FETCH_DISTANCE
import com.judahben149.motiontraction.utils.Constants.STARTING_PAGE_INDEX
import com.judahben149.motiontraction.utils.isNetworkAvailable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MovieService,
    private val database: MovieDatabase
) : MovieRepository {

    @Inject
    lateinit var appContext: Context

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                maxSize = MAX_LOAD_SIZE,
                prefetchDistance = PRE_FETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE
            ),
            remoteMediator = PopularMoviesRXRemoteMediator(
                database,
                moviesService,
                STARTING_PAGE_INDEX
            ),
            pagingSourceFactory = { database.movieDao.getAllMovies() }
        ).flowable
    }


    @SuppressLint("CheckResult")
    @WorkerThread
    override fun getMovieDetail(id: Int): Observable<MovieDetailEntity> {


        if (isNetworkAvailable(appContext)) {
            val apiResult = moviesService.fetchMovieDetail(id)

            apiResult.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        if (result.isSuccessful) {
                            val creditsEntity = result.body()?.toMovieDetailEntity()
                            creditsEntity?.let { database.movieDao.saveMovie(it) }
                        }
                    },
                    { throwable ->

                    })
        }

        return database.movieDao.getMovie(id.toLong())
    }

    @SuppressLint("CheckResult")
    @WorkerThread
    override fun getMovieCredits(id: Int): Observable<CreditsEntity> {

        if (isNetworkAvailable(appContext)) {
            val apiResult = moviesService.fetchMovieCredits(id)

            apiResult.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        if (result.isSuccessful) {
                            val creditsEntity = result.body()?.toCreditsEntity()
                            creditsEntity?.let { database.creditsDao.saveCredit(it) }
                        }
                    },
                    { throwable ->

                    })
        }

        return database.creditsDao.getCredit(id)
    }
}
