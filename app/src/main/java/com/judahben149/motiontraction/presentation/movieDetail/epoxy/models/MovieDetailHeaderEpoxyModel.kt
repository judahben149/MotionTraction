package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailHeaderSectionBinding
import com.judahben149.motiontraction.domain.models.DetailMovie
import com.judahben149.motiontraction.presentation.shared.ViewBindingKotlinModel
import com.judahben149.motiontraction.utils.Constants
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

        Glide.with(context)
            .load(Constants.BACKDROP_BASE_URL + movie.backdropPath)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.INVISIBLE
                    return false
                }

            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivBackdropImage)
    }
}