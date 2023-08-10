package com.judahben149.motiontraction.domain.usecase.movieDetail

import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.generateRandomMovieDetailEntity
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMovieDetailsUseCaseTest {

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieDetailsUseCase

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `getMovieDetails emits OperationResult_Success`() {
        val movieId = 4356
        val movieDetailEntity = generateRandomMovieDetailEntity()

        Mockito.`when`(repository.getMovieDetail(movieId))
            .thenReturn(Observable.just(OperationResult.Success(movieDetailEntity)))

        val testObserver = useCase.getMovieDetail(movieId)
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        testObserver.assertValue { result ->
            result is OperationResult.Success && result.data == movieDetailEntity
        }
    }
}