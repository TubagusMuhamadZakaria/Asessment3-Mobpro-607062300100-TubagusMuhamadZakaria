package com.tubagus0100.resepku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tubagus0100.resepku.model1.PostEntity
import com.tubagus0100.resepku.model1.ResepEntity

@Database(
    entities = [ResepEntity::class, PostEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ResepDatabase : RoomDatabase() {
    abstract fun resepDao(): ResepDao
    abstract fun postDao(): PostDao


    companion object {
        @Volatile
        private var INSTANCE: ResepDatabase? = null

        fun getDatabase(context: Context): ResepDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResepDatabase::class.java,
                    "resep_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
