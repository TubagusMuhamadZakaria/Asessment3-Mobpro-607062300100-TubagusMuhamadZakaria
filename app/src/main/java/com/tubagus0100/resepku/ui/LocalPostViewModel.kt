package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.LocalPostRepository
import com.tubagus0100.resepku.model1.PostEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocalPostViewModel(private val repository: LocalPostRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val posts: StateFlow<List<PostEntity>> = repository.getAllPosts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertPost(post: PostEntity, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.insertPost(post)
            _isLoading.value = false
            onSuccess()
        }
    }

    fun deletePost(post: PostEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deletePost(post)
            _isLoading.value = false
        }
    }

}
