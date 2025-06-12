package com.tubagus0100.resepku.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.sessionDataStore by preferencesDataStore("session")

class SessionManager private constructor(private val context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: SessionManager? = null
        fun getInstance(context: Context): SessionManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SessionManager(context).also { INSTANCE = it }
            }
        }

        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val isLoggedIn: Flow<Boolean> = context.sessionDataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun setLoginStatus(isLoggedIn: Boolean) {
        context.sessionDataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = isLoggedIn
        }
    }
}
