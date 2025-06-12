package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.model.Post
import com.tubagus0100.resepku.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepository(private val apiService: ApiService) {

    fun getAllPosts(): Flow<List<Post>> = flow {
        val response = apiService.getPosts()
        val mappedPosts = response.map {
            Post(
                id = it.id,
                title = it.title,
                body = it.body
            )
        }
        emit(mappedPosts)
    }
}
