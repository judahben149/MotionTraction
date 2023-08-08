package com.judahben149.motiontraction.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movieEntityList: List<MovieResponseEntity.MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieDetailEntity)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovie(movieId: Long): Observable<MovieDetailEntity>

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, MovieResponseEntity.MovieEntity>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}