package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.ResepRepository
import com.tubagus0100.resepku.model1.ResepEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ResepViewModel(private val repository: ResepRepository) : ViewModel() {

    val resepList: StateFlow<List<ResepEntity>> = repository.getAllResep()
        .map { it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertResep(resep: ResepEntity) {
        viewModelScope.launch {
            repository.insertResep(resep)
        }
    }


    fun update(resep: ResepEntity) {
        viewModelScope.launch {
            repository.updateResep(resep)
        }
    }

    fun delete(resep: ResepEntity) {
        viewModelScope.launch {
            repository.deleteResep(resep)
        }
    }
}
