package com.judahben149.motiontraction.presentation.movieDetail.epoxy

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.judahben149.motiontraction.presentation.movieDetail.MovieDetailUiState
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailBodyEpoxyModel
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailCastEpoxyModel
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailHeaderEpoxyModel

class MovieDetailEpoxyController(private val context: Context): TypedEpoxyController<MovieDetailUiState>() {

    override fun buildModels(state: MovieDetailUiState?) {

        if (state == null) {
            // Bind a view to show user data is loading - a progress view
            return
        }

        MovieDetailHeaderEpoxyModel(context, state.movieDetail).id("detail_header").addTo(this)
        MovieDetailBodyEpoxyModel(state.movieDetail).id("detail_body").addTo(this)
        MovieDetailCastEpoxyModel(context, state.credits).id("detail_cast").addTo(this)
    }
}