package com.judahben149.motiontraction.presentation.movieList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.judahben149.motiontraction.domain.scheduler.TestSchedulerProvider
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.GetFavoriteMoviesUseCase
import com.judahben149.motiontraction.domain.usecase.movieList.GetMoviePagedListUseCase
import com.judahben149.motiontraction.generateRandomFavoriteMovieEntityList
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockMovieListUseCase: GetMoviePagedListUseCase

    @Mock
    private lateinit var mockFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Mock
    private lateinit var observer: Observer<in MovieListUIState>

    private lateinit var viewModel: MovieListViewModel

    private val testScheduler = TestScheduler()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        viewModel = MovieListViewModel(mockMovieListUseCase, mockFavoriteMoviesUseCase, testSchedulerProvider)
        viewModel.state.observeForever(observer)
    }

    @After
    fun teardown() {
        viewModel.state.removeObserver(observer)
    }

    @Test
    fun `test updateFilter updates state`() {
        val filterType = MovieFilter.FAVORITES

        viewModel.updateFilter(filterType)

        val actualMovieFilter = viewModel.state.value?.movieFilter
        Truth.assertThat(actualMovieFilter).isEqualTo(filterType)
    }

    @Test
    fun `test notifyErrorHandled updates error state`() {
        viewModel.notifyErrorHandled()
        Truth.assertThat(viewModel.state.value?.isError).isEqualTo(false)
    }

    @Test
    fun `test getFavoriteMovies returns list of favorite movies`() {
        val favoriteMovieEntities = generateRandomFavoriteMovieEntityList(3)

        Mockito.`when`(mockFavoriteMoviesUseCase.getFavoriteMovies())
            .thenReturn(Observable.just(favoriteMovieEntities))

        val testObserver = mockFavoriteMoviesUseCase.getFavoriteMovies().test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Truth.assertThat(testObserver.values()[0]).isEqualTo(favoriteMovieEntities)
    }
}
