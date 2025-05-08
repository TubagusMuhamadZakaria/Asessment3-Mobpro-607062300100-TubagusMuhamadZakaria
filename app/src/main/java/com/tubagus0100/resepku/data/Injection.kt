package com.tubagus0100.resepku.data

import android.content.Context

object Injection {
    fun provideRepository(context: Context): ResepRepository {
        val database = ResepDatabase.getDatabase(context)
        val dao = database.resepDao()
        return ResepRepository(dao)
    }
}
