package com.example.obligor.cache.database.provoders

import android.content.Context
import androidx.room.Room
import com.example.obligor.cache.database.ObligorDatabase

object DatabaseInstanceProvider {

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