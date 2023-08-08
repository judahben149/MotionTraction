package com.judahben149.motiontraction.data.source.local.entity.credits

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credits")
data class CreditsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val castEntity: List<CastEntity>,

    val crewEntity: List<CrewEntity>
)

data class CastEntity(
    val adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String
)

data class CrewEntity(
    val adult: Boolean,
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String
)
