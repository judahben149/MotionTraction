package com.judahben149.motiontraction.domain.mappers

import com.judahben149.motiontraction.data.source.local.entity.credits.CastEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CrewEntity
import com.judahben149.motiontraction.data.source.remote.dto.credits.CastDto
import com.judahben149.motiontraction.data.source.remote.dto.credits.CreditsDto
import com.judahben149.motiontraction.data.source.remote.dto.credits.CrewDto
import com.judahben149.motiontraction.domain.models.credits.Cast
import com.judahben149.motiontraction.domain.models.credits.Credits
import com.judahben149.motiontraction.domain.models.credits.Crew

fun CreditsEntity.toCredits(): Credits {
    return Credits(
        id = this.id,
        cast = this.castEntity.map { it.toCast() },
        crew = this.crewEntity.map { it.toCrew() }
    )
}

fun CastEntity.toCast(): Cast {
    return Cast(
        adult = this.adult,
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
        gender = this.gender,
        id = this.id,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        order = this.order,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun CrewEntity.toCrew(): Crew {
    return Crew(
        adult = this.adult,
        creditId = this.creditId,
        department = this.department,
        gender = this.gender,
        id = this.id,
        job = this.job,
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )
}

fun CreditsDto.toCreditsEntity(): CreditsEntity {
    return CreditsEntity(
        id = this.id,
        castEntity = this.castDto.map { it.toCastEntity() },
        crewEntity = this.crewDto.map { it.toCrewEntity() }
    )
}

fun CastDto.toCastEntity(): CastEntity {
    return CastEntity(
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

fun CrewDto.toCrewEntity(): CrewEntity {
    return CrewEntity(
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