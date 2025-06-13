package com.tubagus0100.resepku.data

import android.content.Context
import com.tubagus0100.resepku.network.ApiConfig

object Injection {

    fun provideRepository(context: Context): ResepRepository {
        val database = ResepDatabase.getDatabase(context)
        return ResepRepository(database.resepDao())
    }

    fun providePreferences(context: Context): PreferenceManager {
        return PreferenceManager.getInstance(context)
    }

    fun providePostRepository(): PostRepository {
        val apiService = ApiConfig.getApiService()
        return PostRepository(apiService)
    }

    fun provideUserRepository(): UserRepository {
        return UserRepository(ApiConfig.getApiService())
    }

    fun provideLocalPostRepository(context: Context): LocalPostRepository {
        val database = ResepDatabase.getDatabase(context)
        return LocalPostRepository(database.postDao())
    }

}