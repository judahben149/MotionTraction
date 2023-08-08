package com.judahben149.motiontraction.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.motiontraction.data.source.local.entity.credits.CreditsEntity
import io.reactivex.Observable

@Dao
interface CreditsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCredit(creditEntity: CreditsEntity)

    @Query("SELECT * FROM credits WHERE id = :creditId")
    fun getCredit(creditId: Int): Observable<CreditsEntity>
}