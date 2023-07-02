package com.example.obligor.cache.database.data_source

import android.util.Log
import com.example.obligor.cache.database.models.PromiserEntity
import com.example.obligor.cache.database.provoders.DatabaseInstanceProvider
import com.example.obligor.domain.core.Dto
import com.example.obligor.domain.models.Promiser
import kotlinx.coroutines.flow.Flow

class PromiserDataSource {

    private val promiserDao by lazy {
        DatabaseInstanceProvider.instance.promiserDao()
    }

    suspend fun getPromiser(name: String): Dto<Promiser>? =
        try {
            promiserDao.getPromiser(name)
        } catch (e: Exception) {
            Log.e(TAG, "getPromiser: error -> ${e.localizedMessage}")
            null
        }

    fun getAllPromisers(): Flow<List<Dto<Promiser>>> =
        promiserDao.getAllPromisers()

    suspend fun addPromiser(name: String) =
        promiserDao.insertPromiser(
            PromiserEntity(
                promiserName = name,
                credit = 0.0,
            )
        )

    suspend fun updatePromiser(name: String, newAmount: Double) =
        promiserDao.updatePromiser(name, newAmount)

    companion object {
        private val TAG = "${this::class.simpleName}"
    }

}