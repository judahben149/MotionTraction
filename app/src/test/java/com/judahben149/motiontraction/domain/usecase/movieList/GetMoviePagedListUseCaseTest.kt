package com.judahben149.motiontraction.domain.usecase.movieList

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.generateRandomMovieEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMoviePagedListUseCaseTest {

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMoviePagedListUseCase

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMoviePagedListUseCase(repository)
    }

    @Test
    fun `getMoviePagedList returns Flowable with PagingData`() {
        val movieEntity = generateRandomMovieEntity()
        val pagingData = PagingData.from(listOf(movieEntity))

        Mockito.`when`(repository.getMovieList()).thenReturn(Flowable.just(pagingData))

        val testFlowable: Flowable<PagingData<MovieEntity>> = useCase.getMoviePagedList()

        testFlowable.test().apply {
            testScheduler.triggerActions()
            assertValue(pagingData)
            assertNoErrors()
            assertComplete()
        }
    }
}