package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.ResepRepository
import com.tubagus0100.resepku.model1.ResepEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ResepViewModel(private val repository: ResepRepository) : ViewModel() {

    val resepList: Flow<List<ResepEntity>> = repository.getAllResep()

    fun insertResep(resep: ResepEntity) {
        viewModelScope.launch {
            repository.insertResep(resep)
        }
    }

    fun updateResep(resep: ResepEntity) {
        viewModelScope.launch {
            repository.updateResep(resep)
        }
    }

    fun deleteResep(resep: ResepEntity) {
        viewModelScope.launch {
            repository.deleteResep(resep)
        }
    }

    fun getResepById(id: Int): Flow<ResepEntity?> {
        return repository.getResepById(id)
    }
}
