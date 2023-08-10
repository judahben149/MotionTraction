package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject  constructor(private val repository: MovieRepository) {

    fun saveFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        repository.saveFavoriteMovie(favoriteMovieEntity)
    }
}