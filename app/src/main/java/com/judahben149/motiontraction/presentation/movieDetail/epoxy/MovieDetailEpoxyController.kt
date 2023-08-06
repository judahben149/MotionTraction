package com.judahben149.motiontraction.presentation.movieDetail.epoxy

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailBodyEpoxyModel
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.models.MovieDetailHeaderEpoxyModel

class MovieDetailEpoxyController(private val context: Context): TypedEpoxyController<DetailMovie>() {

    override fun buildModels(movie: DetailMovie?) {

        if (movie == null) {
            // Bind a view to show user data is loading - a progress view
            return
        }

        MovieDetailHeaderEpoxyModel(context, movie).id("detail_header").addTo(this)
        MovieDetailBodyEpoxyModel(movie).id("detail_body").addTo(this)
    }
}