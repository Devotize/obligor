package com.example.obligor.app

import android.app.Application
import android.util.Log

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: called")

    }

    companion object {
        const val TAG = "App"
    }

}