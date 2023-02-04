package com.example.obligor.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.obligor.cache.database.models.PromiserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PromiserDao {

    @Query("SELECT * FROM ${PromiserEntity.TABLE_NAME} WHERE promiser_name = :promiserName")
    fun getPromiser(promiserName: String): PromiserEntity

    @Query("SELECT * FROM ${PromiserEntity.TABLE_NAME}")
    fun getAllPromisers(): Flow<List<PromiserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPromiser(promiser: PromiserEntity)

    @Query("UPDATE ${PromiserEntity.TABLE_NAME} SET credit = :newCredit WHERE promiser_name = :promiserName")
    suspend fun updatePromiser(promiserName: String, newCredit: Double)

}