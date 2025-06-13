package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.LocalPostRepository
import com.tubagus0100.resepku.model1.PostEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocalPostViewModel(private val repository: LocalPostRepository) : ViewModel() {

    val posts: StateFlow<List<PostEntity>> = repository.getAllPosts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertPost(post: PostEntity, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.insertPost(post)
            onSuccess()
        }
    }

    fun deletePost(post: PostEntity) {
        viewModelScope.launch {
            repository.deletePost(post)
        }
    }

}
