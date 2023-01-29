package com.example.obligor.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.obligor.cache.database.dao.PromiserDao
import com.example.obligor.cache.database.models.PromiserEntity

@Database(entities = [PromiserEntity::class], version = 1)
abstract class ObligorDatabase : RoomDatabase() {
    abstract fun promiserDao(): PromiserDao
}