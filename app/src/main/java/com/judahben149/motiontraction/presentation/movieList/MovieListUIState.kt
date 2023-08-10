package com.judahben149.motiontraction.presentation.movieList

import androidx.paging.PagingData
import com.judahben149.motiontraction.domain.models.favoriteMovies.FavoriteMovie
import com.judahben149.motiontraction.domain.models.movieList.ListMovie
import io.reactivex.Flowable

data class MovieListUIState(
    val movieList: Flowable<PagingData<ListMovie>>? = null,
    val favoriteMovieList: List<FavoriteMovie>? = null,
    val movieFilter: MovieFilter = MovieFilter.ALL,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

enum class MovieFilter {
    ALL,
    FAVORITES
}
