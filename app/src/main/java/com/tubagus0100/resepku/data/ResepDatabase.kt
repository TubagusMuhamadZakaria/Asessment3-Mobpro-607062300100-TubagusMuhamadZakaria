package com.tubagus0100.resepku.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tubagus0100.resepku.model1.ResepEntity

@Database(entities = [ResepEntity::class], version = 1, exportSchema = false)
abstract class ResepDatabase : RoomDatabase() {
    abstract fun resepDao(): ResepDao
}
