package com.example.obligor.interactor

import com.example.obligor.domain.models.Promiser
import com.example.obligor.domain.repositories.PromiserRepository
import com.example.obligor.interactor.scope.InteractorScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppInteractor {

    private val promiserRepository by lazy { PromiserRepository() }

    private val _selectedPromiser = MutableStateFlow(Promiser.EmptyPromiser)
    val selectedPromiser = _selectedPromiser.asStateFlow()

    private val _allPromisers = MutableStateFlow(listOf<Promiser>())
    val allPromisers = _allPromisers.asStateFlow()

    fun addPromiser(name: String) {
        InteractorScope.launch(Dispatchers.IO) {
            promiserRepository.addPromiser(name)
        }
    }

    fun selectedPromiserCreditChange(difference: Double) {
        InteractorScope.launch {
            val promiser = _selectedPromiser.value
            promiserRepository.updatePromiser(
                name = promiser.name,
                newCredit = promiser.credit + difference
            )
        }
    }

    fun selectPromiser(promiser: Promiser) {
        InteractorScope.launch {
            _selectedPromiser.emit(promiser)
        }
    }

    private suspend fun collectAllPromisers(): Flow<List<Promiser>> =
        promiserRepository.getAllPromisers()

    init {
        InteractorScope.launch {
            collectAllPromisers().collect {
                _allPromisers.emit(it)
                if (it.isNotEmpty()) {
                    _selectedPromiser.emit(it.first())
                }
            }
        }
    }

}