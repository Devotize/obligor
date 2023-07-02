package com.example.obligor.app

import android.app.Application
import android.util.Log
import com.example.obligor.cache.database.provoders.DatabaseInstanceProvider
import com.example.obligor.cache.datastore.AppPreferences

class ObligorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: called")
        AppPreferences.initialize(applicationContext)
        DatabaseInstanceProvider.init(applicationContext)
    }

    companion object {
        const val TAG = "App"
    }

}

