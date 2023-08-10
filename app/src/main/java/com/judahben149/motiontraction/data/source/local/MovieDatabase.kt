package com.judahben149.motiontraction.data.source.local

import  androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.judahben149.motiontraction.data.source.local.dao.CreditsDao
import com.judahben149.motiontraction.data.source.local.dao.FavoriteMovieDao
import com.judahben149.motiontraction.data.source.local.dao.MovieDao
import com.judahben149.motiontraction.data.source.local.dao.RemoteKeyDao
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieEntityRemoteKey

@Database(
    entities = [MovieEntity::class, MovieEntityRemoteKey::class, MovieDetailEntity::class, CreditsEntity::class, FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val favoriteMovieDao: FavoriteMovieDao
    abstract val creditsDao: CreditsDao
    abstract val remoteKeyDao: RemoteKeyDao
}