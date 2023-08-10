package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailBodySectionBinding
import com.judahben149.motiontraction.domain.models.credits.Credits
import com.judahben149.motiontraction.domain.models.movieDetail.DetailMovie
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel
import com.judahben149.motiontraction.utils.parseVoteAverage
import com.judahben149.motiontraction.utils.returnDirectorsAndWriters

data class MovieDetailBodyEpoxyModel(
    val movie: DetailMovie,
    val credits: Credits,
    val onLiked: () -> Unit
): ViewBindingKotlinModel<EpoxyModelDetailBodySectionBinding>(R.layout.epoxy_model_detail_body_section) {
    override fun EpoxyModelDetailBodySectionBinding.bind() {
        tvMovieOverview.text = movie.overview
        layoutLike.setOnClickListener {
            onLiked()
            animLike.playAnimation()
            animLike.setMinProgress(0.1f)
        }

        tvRating.text = movie.voteAverage.parseVoteAverage(movie.popularity)
        tvDirectors.text = credits.crew.returnDirectorsAndWriters()
    }
}
