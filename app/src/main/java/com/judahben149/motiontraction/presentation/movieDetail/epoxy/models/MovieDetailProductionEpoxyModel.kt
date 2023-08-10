package com.judahben149.motiontraction.presentation.movieDetail.epoxy.models

import android.content.Context
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelDetailProductionSectionBinding
import com.judahben149.motiontraction.domain.models.credits.Credits
import com.judahben149.motiontraction.domain.models.movieDetail.DetailMovie
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel
import com.judahben149.motiontraction.utils.formatMoney
import com.judahben149.motiontraction.utils.formatProductionCompanies

data class MovieDetailProductionEpoxyModel(
    val context: Context,
    val movie: DetailMovie,
    val credits: Credits
): ViewBindingKotlinModel<EpoxyModelDetailProductionSectionBinding>(R.layout.epoxy_model_detail_production_section) {

    override fun EpoxyModelDetailProductionSectionBinding.bind() {
        tvCrewText.text = movie.productionCompanies.formatProductionCompanies()

        val budgetText = "Budget: $${movie.budget.formatMoney()}\n" + "Revenue: $${movie.revenue.formatMoney()}"
        tvBudget.text = budgetText
    }
}