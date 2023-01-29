package com.example.obligor.app

import android.app.Application
import android.util.Log
import com.example.obligor.cache.database.AppDatabaseInstanceProvider

class ObligorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: called")
        AppDatabaseInstanceProvider.init(applicationContext)
    }

    companion object {
        const val TAG = "App"
    }

}