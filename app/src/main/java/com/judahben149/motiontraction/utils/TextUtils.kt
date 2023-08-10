package com.judahben149.motiontraction.utils

import com.judahben149.motiontraction.domain.models.credits.Crew
import com.judahben149.motiontraction.domain.models.movieDetail.Genre
import com.judahben149.motiontraction.domain.models.movieDetail.ProductionCompany
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt

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
    return if (this.isEmpty()) {
        "N/A"
    } else {
        var genres = ""

        for (i in this.indices) {
            genres += this[i].name

            if (i != this.size - 1) {
                genres += "/"
            }
        }

        genres
    }
}

fun Double.parseVoteAverage(numberVoted: Double): String {
    val trimmedRating = String.format("%.1f", this)
    return "${trimmedRating}/10 (${numberVoted.roundToInt()})"
}

fun List<Crew>.returnDirectorsAndWriters(): String {
    val directorList = this.filter { it.job == "Director" }
    val writerList = this.filter { it.job == "Writer" }

    var directors = ""
    var writers = ""

    for (i in directorList.indices) {
        directors += this[i].name

        if (i != directorList.size - 1) {
            directors += ", "
        }

        if (i == directorList.size - 1){
            directors = "Directors: $directors \n"
        }
    }

    for (i in writerList.indices) {
        writers += this[i].name

        if (i != writerList.size - 1) {
            writers += ", "
        }

        if (i == writerList.size - 1){
            writers = "Writers: $writers"
        }
    }

    return directors + writers
}

fun List<ProductionCompany>.formatProductionCompanies(): String {
    var companyList = ""

    for (i in this.indices) {
        if (i == 1) {
            companyList += this[i].name
        }

        if (i > 1 && i < this.size -1) {
            companyList += "  •  " + this[i].name + "(${ this[i].name })"
        }

        if (i != this.size - 1) {
            companyList += ""
        }
    }

    return companyList
}

fun Double.formatMoney(): String {
    val suffixes = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    var num = this.toLong()
    var tier = 0

    while (num >= 1000) {
        num /= 1000
        tier++
    }

    val formattedValue = String.format("%.1f", this / 1000.0.pow(tier.toDouble()))

    return "$formattedValue${suffixes[tier]}"
}