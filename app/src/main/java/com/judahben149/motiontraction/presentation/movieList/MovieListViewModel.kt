package com.judahben149.motiontraction.presentation.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.domain.mappers.entityToMovieListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(repository: MovieRepositoryImpl): ViewModel() {

    private val _movieListCached = repository.fetchDiscoverMovieListCached().map { pagingData ->
        pagingData.map {  movieEntity ->
            entityToMovieListModel(movieEntity)
        }
    }.cachedIn(viewModelScope)

    val movieListCached get() = _movieListCached
}