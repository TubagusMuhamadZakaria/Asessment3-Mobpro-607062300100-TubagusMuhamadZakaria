package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.PostRepository
import com.tubagus0100.resepku.model.Post
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: StateFlow<List<Post>> = repository.getAllPosts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
}
