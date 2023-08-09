package com.judahben149.motiontraction.domain.usecase

import androidx.paging.PagingData
import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import io.reactivex.Flowable
import javax.inject.Inject

class GetMoviePagedListUseCase @Inject constructor(private val repository: MovieRepositoryImpl) {

    fun getMoviePagedList(): Flowable<PagingData<MovieResponseEntity.MovieEntity>> {
        return repository.getMovieList()
    }
}