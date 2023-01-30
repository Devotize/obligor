package com.example.obligor.domain.repositories

import com.example.obligor.cache.database.data_source.PromiserDataSource
import com.example.obligor.domain.models.Promiser

class PromiserRepository {

    private val promiserDataSource by lazy {
        PromiserDataSource()
    }

    suspend fun getPromiser(name: String): Promiser =
        promiserDataSource.getPromiser(name)?.toDomain() ?: Promiser.EmptyPromiser

    suspend fun getAllPromisers(): List<Promiser> =
        promiserDataSource.getAllPromisers().map { it.toDomain() }

}