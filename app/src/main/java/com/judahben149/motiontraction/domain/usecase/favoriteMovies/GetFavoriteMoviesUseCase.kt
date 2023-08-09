package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.data.repository.MovieRepositoryImpl
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import io.reactivex.Observable
import javax.inject.Inject


class GetFavoriteMoviesUseCase @Inject constructor(private val repository: MovieRepositoryImpl) {

    fun getFavoriteMovies(): Observable<List<FavoriteMovieEntity>> {
        return repository.getFavoriteMovieList()
    }
}