package com.judahben149.motiontraction.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.judahben149.motiontraction.data.source.local.entity.credits.CastEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CrewEntity

class Converters {
    @TypeConverter
    fun fromCastEntityList(value: List<CastEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCastEntityList(value: String): List<CastEntity> {
        val listType = object : TypeToken<List<CastEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCrewEntityList(value: List<CrewEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCrewEntityList(value: String): List<CrewEntity> {
        val listType = object : TypeToken<List<CrewEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}