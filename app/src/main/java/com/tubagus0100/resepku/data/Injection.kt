package com.tubagus0100.resepku.data

import android.content.Context

object Injection {
    fun provideRepository(context: Context): ResepRepository {
        val database = ResepDatabase.getDatabase(context)
        return ResepRepository(database.resepDao())
    }

    fun providePreferences(context: Context): PreferenceManager {
        return PreferenceManager.getInstance(context)
    }
}
