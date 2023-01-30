package com.example.obligor.interactor

import com.example.obligor.domain.models.Promiser
import com.example.obligor.domain.repositories.PromiserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppInteractor {

    private val promiserRepository by lazy { PromiserRepository() }

    private val _selectedPromiser = MutableStateFlow(Promiser.EmptyPromiser)
    val selectedPromiser = _selectedPromiser.asStateFlow()

    private val _allPromisers = MutableStateFlow(listOf<Promiser>())
    val allPromisers = _allPromisers.asStateFlow()

    fun onSelectPromiser(name: String) {
        //TODO
    }

}