package com.judahben149.motiontraction.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class MovieEntityRemoteKey(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)
