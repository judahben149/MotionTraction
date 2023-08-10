package com.judahben149.motiontraction.domain.usecase.favoriteMovies

import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetFavoriteMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getFavoriteMovies(): Observable<List<FavoriteMovieEntity>> {
        return repository.getFavoriteMovieList()
    }
}