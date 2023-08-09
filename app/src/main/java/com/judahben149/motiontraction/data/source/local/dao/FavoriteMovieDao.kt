package com.judahben149.motiontraction.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.source.local.entity.favoriteMovies.FavoriteMovieEntity
import io.reactivex.Observable

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movie")
    fun getAllFavoriteMovies(): Observable<List<FavoriteMovieEntity>>

    @Query("DELETE FROM favorite_movie")
    fun deleteAllFavoriteMovies()
}