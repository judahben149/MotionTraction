package com.judahben149.motiontraction.presentation.movieList

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.judahben149.motiontraction.domain.mappers.toFavoriteMovie
import com.judahben149.motiontraction.domain.mappers.toListMovie
import com.judahben149.motiontraction.domain.usecase.favoriteMovies.GetFavoriteMoviesUseCase
import com.judahben149.motiontraction.domain.usecase.movieList.GetMoviePagedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMoviePagedListUseCase,
    private val favoriteMoviesUseCase: GetFavoriteMoviesUseCase,
) : ViewModel() {

    private val _state: MutableLiveData<MovieListUIState> = MutableLiveData(MovieListUIState())
    val state: LiveData<MovieListUIState> get() = _state


    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMovieList() {
        val filteredMovieList = movieListUseCase.getMoviePagedList()
            .map { it.filter { movieEntity -> movieEntity.posterPath.isNotEmpty() } }
            .map { it.map { movieEntity -> movieEntity.toListMovie() } }

        _state.value = _state.value?.copy(movieList = filteredMovieList.cachedIn(viewModelScope))
    }

    @SuppressLint("CheckResult")
    fun getFavoriteMovies() {
        favoriteMoviesUseCase.getFavoriteMovies()
            .map { it.map { entity -> entity.toFavoriteMovie() } }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _state.value = _state.value?.copy(favoriteMovieList = it)
                }, {

                }
            )
    }

    fun updateFilter(type: MovieType) {
        _state.value = _state.value?.copy(filterType = type)
    }
}