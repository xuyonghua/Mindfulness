package com.deepbay.mindfulness.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepbay.mindfulness.database.AppDatabase.Companion.getDatabase
import com.deepbay.mindfulness.network.Api
import com.deepbay.mindfulness.repository.AppRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val appRepository = AppRepository(getDatabase(application))

    private val _response = MutableLiveData<String>()
    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    val musics = appRepository.getMusicsFromDb()

    private fun get() {
        uiScope.launch {
            try {
                appRepository.refreshMusics()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    init {
        get()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
