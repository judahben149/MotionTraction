package com.judahben149.motiontraction.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.local.entity.MovieListEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieListEntity>)

    @Query("SELECT * FROM MovieListEntity ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, MovieListEntity>

    @Query("DELETE FROM MovieListEntity")
    fun deleteAllArticles()
}