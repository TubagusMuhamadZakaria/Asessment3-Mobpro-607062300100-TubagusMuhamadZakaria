package com.tubagus0100.resepku.data

import androidx.room.*
import com.tubagus0100.resepku.model1.ResepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResep(resep: ResepEntity)

    @Update
    suspend fun updateResep(resep: ResepEntity)

    @Delete
    suspend fun deleteResep(resep: ResepEntity)

    @Query("SELECT * FROM resep ORDER BY id DESC")
    fun getAllResep(): Flow<List<ResepEntity>>

    @Query("SELECT * FROM resep WHERE id = :id")
    fun getResepById(id: Int): Flow<ResepEntity?>

}

