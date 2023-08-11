package com.judahben149.motiontraction.presentation.movieDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.judahben149.motiontraction.domain.scheduler.TestSchedulerProvider
import com.judahben149.motiontraction.domain.usecase.credits.GetMovieCreditsUseCase
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.SaveFavoriteMovieUseCase
import com.judahben149.motiontraction.domain.usecase.movieDetail.GetMovieDetailsUseCase
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockGetMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    private lateinit var mockGetMovieCreditsUseCase: GetMovieCreditsUseCase

    @Mock
    private lateinit var mockSaveFavoriteMovieUseCase: SaveFavoriteMovieUseCase

    @Mock
    private lateinit var observer: Observer<in MovieDetailUiState>

    private lateinit var viewModel: MovieDetailViewModel

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)

        viewModel = MovieDetailViewModel(mockGetMovieDetailsUseCase, mockGetMovieCreditsUseCase, mockSaveFavoriteMovieUseCase, testSchedulerProvider)
        viewModel.state.observeForever(observer)
    }

    @After
    fun teardown() {
        viewModel.state.removeObserver(observer)
    }

    @Test
    fun `test notifyErrorHandled updates error state`() {
        viewModel.notifyErrorHandled()
        Truth.assertThat(viewModel.state.value?.isError).isEqualTo(false)
    }
}