package com.tubagus0100.resepku.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class PreferenceManager private constructor(context: Context) {

    private val appContext = context.applicationContext

    companion object {
        @Volatile
        private var INSTANCE: PreferenceManager? = null

        fun getInstance(context: Context): PreferenceManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceManager(context).also { INSTANCE = it }
            }
        }

        private val IS_GRID_MODE = booleanPreferencesKey("is_grid_mode")
        private val THEME_KEY = stringPreferencesKey("theme_setting")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in") // ✅ Tambahan
    }

    // ✅ Grid mode
    val isGridMode: Flow<Boolean> = appContext.dataStore.data.map { preferences ->
        preferences[IS_GRID_MODE] ?: false
    }

    suspend fun setGridMode(value: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[IS_GRID_MODE] = value
        }
    }

    val themeSetting: Flow<ThemeSetting> = appContext.dataStore.data.map { preferences ->
        when (preferences[THEME_KEY]) {
            ThemeSetting.DARK.name -> ThemeSetting.DARK
            else -> ThemeSetting.LIGHT
        }
    }

    suspend fun setThemeSetting(setting: ThemeSetting) {
        appContext.dataStore.edit { preferences ->
            preferences[THEME_KEY] = setting.name
        }
    }

    val isLoggedIn: Flow<Boolean> = appContext.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun setLoggedIn(value: Boolean) {
        appContext.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = value
        }
    }
}
