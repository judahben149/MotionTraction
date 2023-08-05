package com.judahben149.motiontraction.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.local.entity.MovieEntityRemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRemoteKeys(movieEntityRemoteKeys: List<MovieEntityRemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE id = :movieId")
    fun getRemoteKeysByMovieId(movieId: Long): MovieEntityRemoteKey?

    @Query("DELETE FROM remote_keys")
    fun deleteAllRemoteKeys()
}