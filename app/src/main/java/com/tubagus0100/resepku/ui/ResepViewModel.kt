package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.PreferenceManager
import com.tubagus0100.resepku.data.ResepRepository
import com.tubagus0100.resepku.model1.ResepEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ResepViewModel(
    private val repository: ResepRepository,
    private val pref: PreferenceManager
) : ViewModel() {

    val resepList: StateFlow<List<ResepEntity>> = repository.getAllResep()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    @Suppress("unused")
    val isGridMode: StateFlow<Boolean> = pref.isGridMode
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    @Suppress("unused")
    fun setGridMode(enabled: Boolean) {
        viewModelScope.launch {
            pref.setGridMode(enabled)
        }
    }

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
        return repository.getAllResep().map { list -> list.find { it.id == id } }
    }
}
