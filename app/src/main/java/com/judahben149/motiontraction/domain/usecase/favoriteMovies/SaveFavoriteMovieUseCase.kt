package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject  constructor(private val repository: MovieRepositoryImpl) {

    fun saveFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        repository.saveFavoriteMovie(favoriteMovieEntity)
    }
}