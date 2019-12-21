package com.deepbay.mindfulness.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_album")
data class Album(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "album_name")
    var albumName: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "album_cover")
    var albumCover: String,
    @ColumnInfo(name = "album_image")
    var albumImage: String,
    @ColumnInfo(name = "start_color")
    var startColor: String,
    @ColumnInfo(name = "end_color")
    var endColor: String,
    @ColumnInfo(name = "musics")
    var musics: String
)