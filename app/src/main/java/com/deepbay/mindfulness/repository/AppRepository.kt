package com.deepbay.mindfulness.repository

import androidx.lifecycle.LiveData
import com.deepbay.mindfulness.database.AppDatabase
import com.deepbay.mindfulness.domain.Music
import com.deepbay.mindfulness.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AppRepository(private val database: AppDatabase) {
    suspend fun refreshMusics() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh musics is called")
            val musicList = Api.retrofitService.getMusics(1)
            Timber.d("musicList:%s", musicList.size)
            database.appDatabaseDao.insertAll(musicList)
        }
    }

    fun getMusicsFromDb(): LiveData<List<Music>> {
         return database.appDatabaseDao.getMusics()
    }
}