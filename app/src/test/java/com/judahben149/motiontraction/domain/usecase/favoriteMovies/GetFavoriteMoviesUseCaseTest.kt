package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.generateRandomFavoriteMovieEntityList
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetFavoriteMoviesUseCaseTest {

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetFavoriteMoviesUseCase

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetFavoriteMoviesUseCase(repository)
    }

    @Test
    fun `getFavoriteMovies emits OperationResult_Success`() {
        val favoriteMovieEntityList = generateRandomFavoriteMovieEntityList(5)

        Mockito.`when`(repository.getFavoriteMovieList())
            .thenReturn(Observable.just(favoriteMovieEntityList))

        val testObserver = useCase.getFavoriteMovies()
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        testObserver.assertValue { result ->
            result == favoriteMovieEntityList
        }
    }
}