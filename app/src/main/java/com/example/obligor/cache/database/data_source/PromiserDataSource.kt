package com.example.obligor.cache.database.data_source

import android.util.Log
import com.example.obligor.cache.database.AppDatabaseInstanceProvider
import com.example.obligor.domain.core.Dto
import com.example.obligor.domain.models.Promiser

class PromiserDataSource() {

    private val promiserDao by lazy {
        AppDatabaseInstanceProvider.instance.promiserDao()
    }

    suspend fun getPromiser(name: String): Dto<Promiser>? =
        try {
            promiserDao.getPromiser(name)
        } catch (e: Exception) {
            Log.e(TAG, "getPromiser: error -> ${e.localizedMessage}")
            null
        }

    suspend fun getAllPromisers(): List<Dto<Promiser>> =
        promiserDao.getAllPromisers()

    companion object {
        private val TAG = "${this::class.simpleName}"
    }

}