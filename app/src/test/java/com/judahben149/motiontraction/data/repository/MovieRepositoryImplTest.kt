package com.judahben149.motiontraction.data.repository

import com.google.common.truth.Truth
import com.judahben149.motiontraction.data.source.local.MovieDatabase
import com.judahben149.motiontraction.data.source.local.dao.FavoriteMovieDao
import com.judahben149.motiontraction.data.source.local.dao.MovieDao
import com.judahben149.motiontraction.data.source.remote.MovieService
import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.domain.scheduler.TestSchedulerProvider
import com.judahben149.motiontraction.generateRandomFavoriteMovieEntityList
import com.judahben149.motiontraction.generateRandomMovieDetailEntity
import com.judahben149.motiontraction.utils.NetworkUtils
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class MovieRepositoryImplTest {

    @Mock
    private lateinit var mockMovieService: MovieService

    @Mock
    private lateinit var mockDatabase: MovieDatabase

    @Mock
    private lateinit var mockFavoriteMovieDao: FavoriteMovieDao

    @Mock
    private lateinit var mockMovieDao: MovieDao

    @Mock
    private lateinit var mockNetworkUtils: NetworkUtils

    @Mock
    private lateinit var repository: MovieRepository

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(mockDatabase.favoriteMovieDao).thenReturn(mockFavoriteMovieDao)
        Mockito.`when`(mockDatabase.movieDao).thenReturn(mockMovieDao)

        repository = MovieRepositoryImpl(mockMovieService, mockDatabase, mockNetworkUtils, TestSchedulerProvider(testScheduler))
    }

    @Test
    fun `test getFavoriteMovieList returns ObservableList of FavoriteMovieEntity`() {
        val favMovieEntityList = generateRandomFavoriteMovieEntityList(1)

        Mockito.`when`(mockFavoriteMovieDao.getAllFavoriteMovies())
            .thenReturn(Observable.just(favMovieEntityList))

        val testObserver = repository.getFavoriteMovieList().test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        Truth.assertThat(testObserver.values()[0]).isEqualTo(favMovieEntityList)
    }

    @Test
    fun `test getMovieDetail returns ObservableResultWrapper of MovieDetailEntity`() {
        val movieDetailEntity = generateRandomMovieDetailEntity()

        Mockito.`when`(mockNetworkUtils.isNetworkAvailable())
            .thenReturn(false)

        Mockito.`when`(mockMovieDao.getMovie(movieDetailEntity.movieId.toLong()))
            .thenReturn(Observable.just(movieDetailEntity))

        val testObserver = repository.getMovieDetail(movieDetailEntity.movieId).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val expectedMovieDetailEntity = testObserver.values()[0]

        Truth.assertThat(expectedMovieDetailEntity).isEqualTo(OperationResult.Success(movieDetailEntity))
    }
}