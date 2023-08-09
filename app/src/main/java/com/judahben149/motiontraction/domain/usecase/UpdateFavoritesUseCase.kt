package com.judahben149.motiontraction.domain.usecase

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class UpdateFavoritesUseCase @Inject constructor(private val repository: MovieRepositoryImpl) {

    fun updateIsFavorite(movieId: Int, isFavorite: Boolean) {
        repository.updateIsFavorite(movieId, isFavorite)
    }
}