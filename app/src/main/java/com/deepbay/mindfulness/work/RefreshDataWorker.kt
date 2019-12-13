package com.deepbay.mindfulness.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.deepbay.mindfulness.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        Timber.d("Work request for sync is run")
//        val database = getDatabase(applicationContext)
//        val repository = VideosRepository(database)
//        try {
//            repository.refreshVideos()
//        } catch (e: HttpException) {
//            return Result.retry()
//        }
        return Result.success()
    }
}