package com.tubagus0100.resepku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tubagus0100.resepku.data.LocalPostRepository
import com.tubagus0100.resepku.ui.LocalPostViewModel

class LocalPostViewModelFactory(
    private val repository: LocalPostRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocalPostViewModel::class.java)) {
            return LocalPostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
