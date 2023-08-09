package com.judahben149.motiontraction.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntity
import com.judahben149.motiontraction.data.source.local.entity.movieDetail.MovieDetailEntityUpdate
import com.judahben149.motiontraction.data.source.local.entity.movieList.MovieResponseEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movieEntityList: List<MovieResponseEntity.MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieDetailEntity)

    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMovie(movieId: Long): Observable<MovieDetailEntity>

    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun peekMovie(movieId: Long): Single<MovieDetailEntity?>

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE movieId = :movieId")
    fun updateFavoriteMovieListItem(movieId: Long, isFavorite: Boolean): Single<Int>

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE movieId = :movieId")
    fun updateFavoriteMovieDetailItem(movieId: Int, isFavorite: Boolean): Single<Int>

    @Update(entity = MovieDetailEntity::class)
    fun updateMovieDetail(movieDetailUpdate: MovieDetailEntityUpdate): Single<Int>

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, MovieResponseEntity.MovieEntity>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getAllFavoriteMovies(): Flowable<List<MovieResponseEntity.MovieEntity>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}