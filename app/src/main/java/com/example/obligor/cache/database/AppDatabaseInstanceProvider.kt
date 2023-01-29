package com.example.obligor.cache.database

import android.content.Context
import androidx.room.Room

object AppDatabaseInstanceProvider {

    private var _instance: ObligorDatabase? = null
    val instance by lazy { _instance!! }

    fun init(context: Context) {
        _instance = Room.databaseBuilder(
            context = context,
            klass = ObligorDatabase::class.java,
            name = "obligor-database"
        ).build()
    }

}