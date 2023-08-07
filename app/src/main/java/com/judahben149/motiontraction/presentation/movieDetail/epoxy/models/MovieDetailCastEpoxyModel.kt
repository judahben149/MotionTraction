package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import android.content.Context
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailCastSectionBinding
import com.judahben149.motiontraction.domain.models.credits.Credits
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.MovieCastAdapter
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel

data class MovieDetailCastEpoxyModel(
    val context: Context,
    val credits: Credits
): ViewBindingKotlinModel<EpoxyModelDetailCastSectionBinding>(R.layout.epoxy_model_detail_cast_section) {

    override fun EpoxyModelDetailCastSectionBinding.bind() {
        val adapter = MovieCastAdapter(context)
        rvCast.adapter = adapter

        adapter.submitList(credits.cast)
    }
}