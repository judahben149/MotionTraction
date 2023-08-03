package com.judahben149.motiontraction.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.judahben149.motiontraction.data.local.entity.MovieListEntity
import com.judahben149.motiontraction.data.local.entity.MovieEntityRemoteKey

@Database(entities = [MovieListEntity::class, MovieEntityRemoteKey::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val remoteKeyDao: RemoteKeyDao
}