package com.judahben149.motiontraction.utils

import com.judahben149.motiontraction.domain.models.movieDetail.Genre
import java.text.SimpleDateFormat
import java.util.Locale

fun String.parseFriendlyDate(): String {

    return if (this.isEmpty()) {
        "N/A"
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(this)

        val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
        outputFormat.format(date!!)
    }
}

fun Int.parseRunTime(): String {
    val hours: Int
    val minutes: Int

    return if (this >= 60) {
        hours = this / 60
        minutes = this % 60
        "${hours}h ${minutes}m"
    }
    else {
        minutes = this
        "${minutes}m"
    }
}

fun List<Genre>.parseGenres(): String {
    if (this.isEmpty()) {
        return "N/A"
    } else {
        var genres = ""

        for (i in this.indices) {
            genres += this[i].name

            if (i != this.size - 1) {
                genres += "/"
            }
        }

//        genres.dropLast(3)
        return genres
    }
}