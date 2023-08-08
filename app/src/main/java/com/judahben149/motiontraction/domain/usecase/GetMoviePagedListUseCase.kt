package com.judahben149.motiontraction.domain.usecase

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class GetMoviePagedListUseCase @Inject constructor(private val repository: MovieRepositoryImpl) {


}