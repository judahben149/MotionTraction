package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.remote.dto.credits.CastDto
import com.judahben149.motiontraction.data.remote.dto.credits.CreditsDto
import com.judahben149.motiontraction.data.remote.dto.credits.CrewDto
import com.judahben149.motiontraction.domain.models.credits.Cast
import com.judahben149.motiontraction.domain.models.credits.Credits
import com.judahben149.motiontraction.domain.models.credits.Crew

fun CreditsDto.toCredits(): Credits {
    return Credits(
        id = this.id ?: 0,
        cast = this.castDto?.map { it.toCast() } ?: emptyList(),
        crew = this.crewDto?.map { it.toCrew() } ?: emptyList()
    )
}

fun CastDto.toCast(): Cast {
    return Cast(
        adult = this.adult ?: false,
        castId = this.castId ?: 0,
        character = this.character ?: "",
        creditId = this.creditId ?: "",
        gender = this.gender ?: 0,
        id = this.id ?: 0,
        knownForDepartment = this.knownForDepartment ?: "",
        name = this.name ?: "",
        order = this.order ?: 0,
        originalName = this.originalName ?: "",
        popularity = this.popularity ?: 0.00,
        profilePath = this.profilePath ?: ""
    )
}

fun CrewDto.toCrew(): Crew {
    return Crew(
        adult = this.adult ?: false,
        creditId = this.creditId ?: "",
        department = this.department ?: "",
        gender = this.gender ?: 0,
        id = this.id ?: 0,
        job = this.job ?: "",
        knownForDepartment = this.knownForDepartment ?: "",
        name = this.name ?: "",
        originalName = this.originalName ?: "",
        popularity = this.popularity ?: 0.00,
        profilePath = this.profilePath ?: ""
    )
}