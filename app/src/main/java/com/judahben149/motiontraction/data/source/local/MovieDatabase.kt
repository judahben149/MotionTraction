package com.judahben149.motiontraction.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.judahben149.motiontraction.data.source.local.entity.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.MovieEntityRemoteKey
import com.judahben149.motiontraction.data.source.local.entity.MovieResponseEntity

@Database(entities = [MovieResponseEntity.MovieEntity::class, MovieEntityRemoteKey::class, MovieDetailEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val remoteKeyDao: RemoteKeyDao
}