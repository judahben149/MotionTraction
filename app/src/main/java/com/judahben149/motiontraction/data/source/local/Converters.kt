package com.judahben149.motiontraction.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.judahben149.motiontraction.data.source.local.entity.credits.CastEntity
import com.judahben149.motiontraction.data.source.local.entity.credits.CrewEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.GenreEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCompanyEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.ProductionCountryEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.SpokenLanguageEntity

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

    @TypeConverter
    fun fromGenresList(genres: List<GenreEntity>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenresList(json: String): List<GenreEntity> {
        val type = object : TypeToken<List<GenreEntity>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromProductionCompaniesList(companies: List<ProductionCompanyEntity>): String {
        return Gson().toJson(companies)
    }

    @TypeConverter
    fun toProductionCompaniesList(json: String): List<ProductionCompanyEntity> {
        val type = object : TypeToken<List<ProductionCompanyEntity>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromProductionCountriesList(countries: List<ProductionCountryEntity>): String {
        return Gson().toJson(countries)
    }

    @TypeConverter
    fun toProductionCountriesList(json: String): List<ProductionCountryEntity> {
        val type = object : TypeToken<List<ProductionCountryEntity>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromSpokenLanguagesList(languages: List<SpokenLanguageEntity>): String {
        return Gson().toJson(languages)
    }

    @TypeConverter
    fun toSpokenLanguagesList(json: String): List<SpokenLanguageEntity> {
        val type = object : TypeToken<List<SpokenLanguageEntity>>() {}.type
        return Gson().fromJson(json, type)
    }
}