package com.judahben149.motiontraction.data.source.remote.dto.credits

import com.google.gson.annotations.SerializedName

data class CreditsDto(
    val id: Int,

    @SerializedName("cast")
    val castDto: List<CastDto>,

    @SerializedName("crew")
    val crewDto: List<CrewDto>
)