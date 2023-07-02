package com.example.obligor.cache.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AppPreferences private constructor() {

    companion object {
        private val Context._store: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private var storeN: DataStore<Preferences>? = null
        private val store: DataStore<Preferences> by lazy { storeN!! }
        fun initialize(context: Context) {
            storeN = context._store
        }

        val SELECTED_PROMISER_KEY = stringPreferencesKey("selected_promiser")
        val selectedPromiser: Flow<String> by lazy {
            store.data.map {
                it[SELECTED_PROMISER_KEY].orEmpty()
            }
        }

        fun setSelectedPromiser(promiserName: String) {
            CoroutineScope(Dispatchers.Default).launch {
                store.edit { settings ->
                    settings[SELECTED_PROMISER_KEY] = promiserName
                }
            }
        }

    }

}