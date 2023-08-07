package com.judahben149.motiontraction.presentation.shared.epoxy.models

import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.EpoxyModelProgressScreenBinding
import com.judahben149.motiontraction.presentation.shared.epoxy.ViewBindingKotlinModel

data class ProgressScreenEpoxyModel(
    private val progressBarColor: String
): ViewBindingKotlinModel<EpoxyModelProgressScreenBinding>(R.layout.epoxy_model_progress_screen) {
    override fun EpoxyModelProgressScreenBinding.bind() {

    }
}