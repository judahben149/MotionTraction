package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailBodySectionBinding
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel

data class MovieDetailBodyEpoxyModel(
    val movie: DetailMovie
): ViewBindingKotlinModel<EpoxyModelDetailBodySectionBinding>(R.layout.epoxy_model_detail_body_section) {
    override fun EpoxyModelDetailBodySectionBinding.bind() {
        tvMovieOverview.text = movie.overview
    }
}
