package com.judahben149.motiontraction.presentation.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.judahben149.motiontraction.domain.mappers.toListMovie
import com.judahben149.motiontraction.domain.models.movieList.ListMovie
import com.judahben149.motiontraction.domain.usecase.GetMoviePagedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieListUseCase: GetMoviePagedListUseCase): ViewModel() {

    private val _state: MutableLiveData<MovieListUIState> = MutableLiveData(MovieListUIState())
    val state: LiveData<MovieListUIState> get() = _state

    private var movieList: Flowable<PagingData<ListMovie>>? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMovieList(): Flowable<PagingData<ListMovie>> {
        return if (_state.value?.movieList == null) {
            val filteredMovieList = movieListUseCase.getMoviePagedList()
                .map { it.filter { movieEntity -> movieEntity.posterPath.isNotEmpty() } }
                .map { it.map { movieEntity -> movieEntity.toListMovie() } }

            val filteredAndSortedMovieList = when(_state.value!!.filterType) {
                MovieType.ALL -> filteredMovieList
                MovieType.FAVORITES -> {
                    filteredMovieList.map { pagingData ->
                        pagingData.filter { it.isFavorite }
                    }
                }
            }

            _state.value = _state.value?.copy(movieList = filteredAndSortedMovieList.cachedIn(viewModelScope))
            _state.value?.movieList!!
        } else {
            _state.value?.movieList!!
        }
    }

    fun updateFilter(type: MovieType) {
        _state.value = _state.value?.copy(filterType = type)
    }
}