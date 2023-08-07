package com.judahben149.motiontraction.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class MovieEntityRemoteKey(

    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val prev: Int?,
    val next: Int?
)
