package com.example.obligor.domain.repositories

import com.example.obligor.cache.database.data_source.PromiserDataSource
import com.example.obligor.domain.models.Promiser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PromiserRepository {

    private val promiserDataSource by lazy {
        PromiserDataSource()
    }

    suspend fun getPromiser(name: String): Promiser =
        promiserDataSource.getPromiser(name)?.toDomain() ?: Promiser.EmptyPromiser

    suspend fun getAllPromisers(): Flow<List<Promiser>> =
        promiserDataSource.getAllPromisers().map { it.map { it.toDomain() } }

    suspend fun addPromiser(name: String) =
        promiserDataSource.addPromiser(name)

    suspend fun updatePromiser(name: String, newCredit: Double) {
        promiserDataSource.updatePromiser(name, newCredit)
    }

}