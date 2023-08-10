package com.judahben149.motiontraction.domain.usecase.credits

import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import com.judahben149.motiontraction.utils.OperationResult
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getMovieCredits(movieId: Int): Observable<OperationResult<CreditsEntity>> {
        return repository.getMovieCredits(movieId)
    }
}