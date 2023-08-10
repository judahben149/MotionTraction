package com.judahben149.motiontraction.domain.usecase.movieList

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetMoviePagedListUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getMoviePagedList(): Flowable<PagingData<MovieEntity>> {
        return repository.getMovieList()
    }
}