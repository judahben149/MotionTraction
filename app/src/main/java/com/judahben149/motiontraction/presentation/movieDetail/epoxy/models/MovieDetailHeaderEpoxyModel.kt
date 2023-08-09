package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import android.content.Context
import android.view.View
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailHeaderSectionBinding
import com.judahben149.motiontraction.domain.models.movieDetail.DetailMovie
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel
import com.judahben149.motiontraction.utils.loadImage
import com.judahben149.motiontraction.utils.parseFriendlyDate
import com.judahben149.motiontraction.utils.parseGenres
import com.judahben149.motiontraction.utils.parseRunTime

data class MovieDetailHeaderEpoxyModel(
    val context: Context,
    val movie: DetailMovie
): ViewBindingKotlinModel<EpoxyModelDetailHeaderSectionBinding>(R.layout.epoxy_model_detail_header_section) {

    override fun EpoxyModelDetailHeaderSectionBinding.bind() {
        tvMovieTitle.text = movie.title
        tvMovieDate.text = movie.releaseDate.parseFriendlyDate()
        tvMovieRuntime.text = movie.runtime.parseRunTime()
        tvMovieGenre.text = movie.genres.parseGenres()

        if (tvMovieGenre.text.length > 30) {
            tvMovieDate.textSize = 11F
            tvMovieGenre.textSize = 11F
            tvMovieRuntime.textSize = 11F
        }

        ivBackdropImage.loadImage(context, movie.backdropPath) { progressBar.visibility = View.INVISIBLE }
    }
}