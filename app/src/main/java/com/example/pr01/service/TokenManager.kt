package com.example.pr01.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class TokenManager(private val context: Context) {
    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val USER_ID = stringPreferencesKey("user_id")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[ACCESS_TOKEN] = token
            }
        }
    }

    suspend fun saveUserId(userId: String) {
        context.dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[USER_ID] = userId
            }
        }
    }

    suspend fun tokenFlow(): String? =
        context.dataStore.data.map { preferences -> preferences[ACCESS_TOKEN] }.first()

    suspend fun userIdFlow(): String? =
        context.dataStore.data.map { preferences -> preferences[USER_ID] }.first()
}
