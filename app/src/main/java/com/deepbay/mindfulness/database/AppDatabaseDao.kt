package com.deepbay.mindfulness.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.deepbay.mindfulness.domain.Music

@Dao
interface AppDatabaseDao {
    @Insert
    fun insert(music: Music)

    @Update
    fun update(music: Music)

    @Query("SELECT * from t_music WHERE _id = :key")
    fun get(key: Long): Music?

    @Query("SELECT * from t_music WHERE _id = :key")
    fun getMusicWithId(key: Long): LiveData<Music>

    @Query("DELETE FROM t_music")
    fun clear()

    @Query("SELECT * FROM t_music ORDER BY _id DESC")
    fun getMusics(): LiveData<List<Music>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<Music>)
}