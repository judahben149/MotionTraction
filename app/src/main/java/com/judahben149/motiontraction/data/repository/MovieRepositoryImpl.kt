package com.judahben149.motiontraction.data.repository

import android.annotation.SuppressLint
import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.judahben149.motiontraction.data.source.local.MovieDatabase
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntity
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
import com.judahben149.motiontraction.utils.NetworkUtils
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MovieService,
    private val database: MovieDatabase,
    private val networkUtils: NetworkUtils
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieList(): Flowable<PagingData<MovieEntity>> {

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
    override fun saveFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        Single.fromCallable { database.favoriteMovieDao.saveFavoriteMovie(favoriteMovieEntity) }
            .subscribeOn(Schedulers.io()).subscribe()
    }


    override fun getFavoriteMovieList(): Observable<List<FavoriteMovieEntity>> {
        return database.favoriteMovieDao.getAllFavoriteMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    @WorkerThread
    override fun getMovieDetail(id: Int): Observable<OperationResult<MovieDetailEntity>> {

        if (networkUtils.isNetworkAvailable()) {
            return try {
                moviesService.fetchMovieDetail(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap { result ->
                        if (result.isSuccessful) {
                            val detailEntity = result.body()?.toMovieDetailEntity()

                            if (detailEntity != null) {
                                database.movieDao.saveMovie(detailEntity)
                                Observable.just(OperationResult.Success(detailEntity))
                            } else {
                                Observable.just(OperationResult.Error(Throwable("Empty API result")))
                            }
                        } else {
                            Observable.just(OperationResult.Error(Throwable("Error fetching movie details - ${result.message()}")))
                        }
                    }
            } catch (e: HttpException) {
                Observable.just(OperationResult.Error(Throwable("API Error - ${e.message.toString()}")))
            } catch (e: IOException) {
                Observable.just(OperationResult.Error(Throwable("Could not establish connection - ${e.message.toString()}")))
            } catch (e: Exception) {
                Observable.just(OperationResult.Error(Throwable("General exception - ${e.message.toString()}")))
            }
        }

        return database.movieDao.getMovie(id.toLong())
            .flatMap {
                Observable.just<OperationResult<MovieDetailEntity>>(OperationResult.Success(it))
            }
            .onErrorReturn { OperationResult.Error(Throwable(it.message.toString())) }
    }


    @WorkerThread
    override fun getMovieCredits(id: Int): Observable<OperationResult<CreditsEntity>> {

        if (networkUtils.isNetworkAvailable()) {
            return try {
                moviesService.fetchMovieCredits(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap { result ->
                        if (result.isSuccessful) {
                            val creditsEntity = result.body()?.toCreditsEntity()

                            if (creditsEntity != null) {
                                database.creditsDao.saveCredit(creditsEntity)
                                Observable.just(OperationResult.Success(creditsEntity))
                            } else {
                                Observable.just(OperationResult.Error(Throwable("Empty API result")))
                            }
                        } else {
                            Observable.just(OperationResult.Error(Throwable("Error fetching movie credits - ${result.message()}")))
                        }
                    }
            } catch (e: HttpException) {
                Observable.just(OperationResult.Error(Throwable("API Error - ${e.message.toString()}")))
            } catch (e: IOException) {
                Observable.just(OperationResult.Error(Throwable("Could not establish connection - ${e.message.toString()}")))
            } catch (e: Exception) {
                Observable.just(OperationResult.Error(Throwable("General exception - ${e.message.toString()}")))
            }
        }

        return database.creditsDao.getCredit(id)
            .flatMap { Observable.just<OperationResult<CreditsEntity>>(OperationResult.Success(it)) }
            .onErrorReturn { OperationResult.Error(Throwable(it.message.toString())) }
    }
}
