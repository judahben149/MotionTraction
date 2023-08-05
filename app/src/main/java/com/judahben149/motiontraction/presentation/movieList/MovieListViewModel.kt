package com.judahben149.motiontraction.presentation.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity
import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.domain.mappers.toListMovie
import com.judahben149.motiontraction.domain.models.ListMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepositoryImpl): ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMovieList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>> {
        return repository.getMovieList()
//            .map { it.filter { it.poster_path != null } }
//            .map { it.map { entity -> entity.toListMovie() } }
            .cachedIn(viewModelScope)
    }
}