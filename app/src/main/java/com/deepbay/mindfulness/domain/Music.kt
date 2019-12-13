package com.deepbay.mindfulness.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_music")
data class Music(
    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0L,
    @ColumnInfo(name = "music_name")
    var musicName: String,
    @ColumnInfo(name = "music_url")
    var musicUrl: String,
    @ColumnInfo(name = "duration")
    var duration: Long = 0L
)