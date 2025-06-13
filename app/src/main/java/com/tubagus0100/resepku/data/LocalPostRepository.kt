package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.model1.PostEntity
import kotlinx.coroutines.flow.Flow

class LocalPostRepository(private val postDao: PostDao) {
    fun getAllPosts(): Flow<List<PostEntity>> = postDao.getAllPosts()
    suspend fun insertPost(post: PostEntity) = postDao.insert(post)
}
