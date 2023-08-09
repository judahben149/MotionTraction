package com.judahben149.motiontraction.presentation.movieDetail.epoxy

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.judahben149.motiontraction.presentation.movieDetail.MovieDetailUiState
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailBodyEpoxyModel
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailCastEpoxyModel
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailHeaderEpoxyModel
import com.judahben149.motiontraction.presentation.shared.epoxy.models.ProgressScreenEpoxyModel

class MovieDetailEpoxyController(
    private val context: Context,
    private val onMovieLiked: () -> Unit,
    private val onLoadedItemsComplete: () -> Unit
) : TypedEpoxyController<MovieDetailUiState>() {

    override fun buildModels(state: MovieDetailUiState?) {

        if (state == null || state.isLoading) {
            ProgressScreenEpoxyModel("").id("progress_screen").addTo(this)
        } else {
            onLoadedItemsComplete()
            MovieDetailHeaderEpoxyModel(context, state.movieDetail).id("detail_header").addTo(this)
            MovieDetailBodyEpoxyModel(state.movieDetail, { onMovieLiked() }).id("detail_body").addTo(this)
            MovieDetailCastEpoxyModel(context, state.credits).id("detail_cast").addTo(this)
        }
    }
}