package com.judahben149.motiontraction.presentation.movieList

import androidx.paging.PagingData
import com.judahben149.motiontraction.domain.models.movieList.ListMovie
import io.reactivex.Flowable

data class MovieListUIState(
    val movieList: Flowable<PagingData<ListMovie>>? = null,
    val filterType: MovieType = MovieType.ALL
)

enum class MovieType {
    ALL,
    FAVORITES
}
