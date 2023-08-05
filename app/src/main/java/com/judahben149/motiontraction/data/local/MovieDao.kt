package com.judahben149.motiontraction.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.local.entity.MovieResponseEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieResponseEntity.MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, MovieResponseEntity.MovieEntity>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}