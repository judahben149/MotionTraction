package com.judahben149.motiontraction.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.domain.mappers.toCredits
import com.judahben149.motiontraction.domain.mappers.toDetailMovie
import com.judahben149.motiontraction.domain.usecase.credits.GetMovieCreditsUseCase
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.SaveFavoriteMovieUseCase
import com.judahben149.motiontraction.domain.usecase.movieDetail.GetMovieDetailsUseCase
import com.judahben149.motiontraction.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailsUseCase: GetMovieDetailsUseCase,
    private val creditsUseCase: GetMovieCreditsUseCase,
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
) : ViewModel() {

    private var _state: MutableLiveData<MovieDetailUiState> = MutableLiveData(MovieDetailUiState())
    val state: LiveData<MovieDetailUiState> = _state

    private lateinit var disposable: Disposable


    fun getMovie(movieId: Int) {
        clearError()

        Observable.merge(
            detailsUseCase.getMovieDetail(movieId).subscribeOn(Schedulers.io()),
            creditsUseCase.getMovieCredits(movieId).subscribeOn(Schedulers.io())
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieObserver())
    }

    private fun getMovieObserver(): Observer<OperationResult<Any>> {
        return object : Observer<OperationResult<Any>> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
                toggleLoadingState(true)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                postError(e.message.toString())
            }

            override fun onComplete() {
                toggleLoadingState(false)
            }

            override fun onNext(item: OperationResult<Any>) {
                when (item) {
                    is OperationResult.Success -> {
                        handleSuccessResult(item.data)
                    }

                    is OperationResult.Error -> {
                        handleErrorResult(item.throwable.localizedMessage ?: "An error occurred")
                    }
                }
            }
        }
    }

    private fun handleSuccessResult(item: Any) {
        if (item is MovieDetailEntity) {
            _state.value = _state.value?.copy(
                movieDetail = item.toDetailMovie(),
                isGetMovieDetailSuccessful = true
            )
            toggleLoadingState(!(_state.value?.isGetMovieDetailSuccessful == true && _state.value?.isGetMovieCastSuccessful == true))
        } else if (item is CreditsEntity) {
            _state.value = _state.value?.copy(
                credits = item.toCredits(),
                isGetMovieCastSuccessful = true
            )
            toggleLoadingState(!(_state.value?.isGetMovieDetailSuccessful == true && _state.value?.isGetMovieCastSuccessful == true))
        }
    }

    private fun handleErrorResult(errorMessage: String) { postError(errorMessage) }

    fun addToFavorites() {
        _state.value?.movieDetail?.apply {
            val favoriteMovieEntity = FavoriteMovieEntity(
                movieId = this.id,
                adult = this.adult,
                backdropPath = this.backdropPath,
                originalTitle = this.originalTitle,
                popularity = this.popularity,
                posterPath = this.posterPath,
                releaseDate = this.releaseDate,
                runtime = this.runtime,
                tagline = this.tagline,
                title = this.title,
                voteAverage = this.voteAverage,
                voteCount = this.voteCount
            )

            saveFavoriteMovieUseCase.saveFavoriteMovie(favoriteMovieEntity)
        }
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        _state.postValue(_state.value?.copy(isLoading = isLoading))
    }

    private fun postError(errorMessage: String) {
        _state.postValue(_state.value?.copy(isError = true, errorMessage = errorMessage))
    }

    private fun clearError() {
        _state.postValue(_state.value?.copy(isError = false))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}