package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailCastSectionBinding
import com.judahben149.motiontraction.presentation.shared.ViewBindingKotlinModel

data class MovieDetailCastEpoxyModel(
    val context: Context,
    val castList: List<String> //change this to the cast detail model
): ViewBindingKotlinModel<EpoxyModelDetailCastSectionBinding>(R.layout.epoxy_model_detail_cast_section) {

    override fun EpoxyModelDetailCastSectionBinding.bind() {
//        rvCast.adapter = MovieCastAdapter()
    }
}

//class MovieCastAdapter(): RecyclerView.Adapter