package com.example.obligor.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.obligor.domain.models.Promiser
import com.example.obligor.utils.logXertz
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    _allPromisers: Flow<List<Promiser>>,
    _selectedPromiser: Flow<Promiser>,
) {
    logXertz("home screen inited")

    val promisers = _allPromisers.collectAsState(initial = listOf())
    val selectedPromiser = _selectedPromiser.collectAsState(initial = Promiser.EmptyPromiser)


}