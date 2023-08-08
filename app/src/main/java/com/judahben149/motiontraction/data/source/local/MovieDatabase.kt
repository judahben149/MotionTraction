package com.judahben149.motiontraction.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.judahben149.motiontraction.data.source.local.dao.CreditsDao
import com.judahben149.motiontraction.data.source.local.dao.MovieDao
import com.judahben149.motiontraction.data.source.local.dao.RemoteKeyDao
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntityRemoteKey
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity

@Database(entities = [MovieResponseEntity.MovieEntity::class, MovieEntityRemoteKey::class, MovieDetailEntity::class, CreditsEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val creditsDao: CreditsDao
    abstract val remoteKeyDao: RemoteKeyDao
}