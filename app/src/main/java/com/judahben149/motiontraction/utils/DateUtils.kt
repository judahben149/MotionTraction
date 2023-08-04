package com.judahben149.motiontraction.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.parseFriendlyDate(): String {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    return outputFormat.format(date!!)
}