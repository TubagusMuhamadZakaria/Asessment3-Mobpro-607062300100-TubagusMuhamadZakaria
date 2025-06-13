package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.model.Post
import com.tubagus0100.resepku.model.PostRequest
import com.tubagus0100.resepku.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class PostRepository(private val apiService: ApiService) {

    private val localPosts = MutableStateFlow<List<Post>>(emptyList())

    fun getAllPosts(): Flow<List<Post>> = flow {
        val response = apiService.getPosts()
        val mappedPosts = response.map {
            Post(id = it.id, title = it.title, body = it.body)
        }
        localPosts.value = mappedPosts
        emit(mappedPosts)
    }

    suspend fun addPost(post: Post): Boolean {
        return try {
            val request = PostRequest(title = post.title, body = post.body, userId = 1)
            val response = apiService.createPost(request)
            if (response.isSuccessful) {
                localPosts.update { current -> listOf(post) + current }
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    fun getLocalPosts(): Flow<List<Post>> = localPosts
}
