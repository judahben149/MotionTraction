package com.judahben149.motiontraction.domain.usecase.credits

import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.generateRandomCreditsEntity
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMovieCreditsUseCaseTest {

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieCreditsUseCase

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMovieCreditsUseCase(repository)
    }

    @Test
    fun `getMovieCredits emits OperationResult_Success`() {
        val movieId = 2034
        val creditsEntity = generateRandomCreditsEntity()

        Mockito.`when`(repository.getMovieCredits(movieId))
            .thenReturn(Observable.just(OperationResult.Success(creditsEntity)))

        val testObserver = useCase.getMovieCredits(movieId)
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        testObserver.assertValue { result ->
            result is OperationResult.Success && result.data == creditsEntity
        }
    }

//    @Test
//    fun `getMovieCredits emits OperationResult_Failure`() {
//        val movieId = 3347
//        val error = Throwable("Some error")
//
//        Mockito.`when`(repository.getMovieCredits(movieId))
//            .thenReturn(Observable.just(OperationResult.Error(error)))
//
//        val testObserver = useCase.getMovieCredits(movieId)
//            .observeOn(testScheduler)
//            .test()
//
//        testScheduler.triggerActions()
//        testObserver.assertError { throwable ->
//            (throwable as? OperationResult.Error)?.throwable == error
//        }
//    }
}