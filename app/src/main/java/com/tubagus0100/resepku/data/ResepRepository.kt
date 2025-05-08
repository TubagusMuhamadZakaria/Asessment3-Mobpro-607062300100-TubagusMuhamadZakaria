package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.model1.ResepEntity
import kotlinx.coroutines.flow.Flow

class ResepRepository(private val resepDao: ResepDao) {

    fun getAllResep(): Flow<List<ResepEntity>> = resepDao.getAllResep()

    suspend fun insertResep(resep: ResepEntity) = resepDao.insertResep(resep)

    suspend fun updateResep(resep: ResepEntity) = resepDao.updateResep(resep)

    suspend fun deleteResep(resep: ResepEntity) = resepDao.deleteResep(resep)
}
