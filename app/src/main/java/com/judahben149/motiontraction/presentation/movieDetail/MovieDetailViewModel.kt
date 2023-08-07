package com.judahben149.motiontraction.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.domain.mappers.toCredits
import com.judahben149.motiontraction.domain.mappers.toDetailMovie
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.domain.models.credits.Credits
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieRepositoryImpl) :
    ViewModel() {

    private var _state: MutableLiveData<MovieDetailUiState> = MutableLiveData(MovieDetailUiState())
    val state: LiveData<MovieDetailUiState> = _state

    private lateinit var disposable: Disposable
    private lateinit var creditsDisposable: Disposable

    fun getMovieDetail(movieId: Int) {
        repository.getMovieDetail(movieId)
            .map { it.toDetailMovie() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieDetailObserver())
    }

    fun getMovieCredits(movieId: Int) {
        repository.getMovieCredits(movieId)
            .map { it.toCredits() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMovieCreditsObserver())
    }

    private fun getMovieDetailObserver(): Observer<DetailMovie> {
        return object : Observer<DetailMovie> {
            override fun onSubscribe(d: Disposable) {
                // show progress bar
                disposable = d
                toggleLoadingState(true)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                _state.value = _state.value?.copy(
                    isError = true,
                    errorMessage = e.message.toString(),
                )
            }

            override fun onComplete() {}

            override fun onNext(movie: DetailMovie) {
                _state.value = _state.value?.copy(
                    movieDetail = movie,
                    isGetMovieDetailSuccessful = true
                )

                toggleLoadingState(!(_state.value?.isGetMovieDetailSuccessful == true && _state.value?.isGetMovieCastSuccessful == true))
            }
        }
    }

    private fun getMovieCreditsObserver(): Observer<Credits> {
        return object : Observer<Credits> {
            override fun onSubscribe(d: Disposable) {
                // show progress bar
                creditsDisposable = d
                toggleLoadingState(true)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                _state.value = _state.value?.copy(
                    isError = true,
                    errorMessage = e.message.toString(),
                    isLoading = false
                )
            }

            override fun onComplete() {
                toggleLoadingState(isLoading = false)
            }

            override fun onNext(credits: Credits) {
                if (_state.value?.isGetMovieDetailSuccessful == true) {
                    _state.value = _state.value?.copy(
                        credits = credits,
                        isGetMovieCastSuccessful = true
                    )
                }

                toggleLoadingState(!(_state.value?.isGetMovieDetailSuccessful == true && _state.value?.isGetMovieCastSuccessful == true))
            }
        }
    }


    private fun toggleLoadingState(isLoading: Boolean) {
        _state.postValue(_state.value?.copy(isLoading = isLoading))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        creditsDisposable.dispose()
    }
}