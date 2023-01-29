package com.example.obligor.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.obligor.cache.database.models.PromiserEntity

@Dao
interface PromiserDao {

    @Query("SELECT * FROM ${PromiserEntity.TABLE_NAME} WERE name IN (:promiserName)")
    suspend fun getPromiser(promiserName: String): PromiserEntity

    @Query("SELECT * FROM ${PromiserEntity.TABLE_NAME}")
    suspend fun getAllPromisers(): List<PromiserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPromiser(promiser: PromiserEntity)

}