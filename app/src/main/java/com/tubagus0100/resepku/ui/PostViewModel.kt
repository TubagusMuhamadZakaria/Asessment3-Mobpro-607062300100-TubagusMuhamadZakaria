package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.PostRepository
import com.tubagus0100.resepku.model.Post
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: StateFlow<List<Post>> = repository.getLocalPosts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getAllPosts()
        }
    }

    fun addPost(post: Post, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.addPost(post)
            onResult(success)
        }
    }
}
