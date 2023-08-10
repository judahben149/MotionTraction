package com.judahben149.motiontraction.domain.usecase.movieDetail

import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getMovieDetail(movieId: Int): Observable<OperationResult<MovieDetailEntity>> {
        return repository.getMovieDetail(movieId)
    }
}