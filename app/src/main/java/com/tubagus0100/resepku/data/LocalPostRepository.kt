package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.model1.PostEntity
import kotlinx.coroutines.flow.Flow

class LocalPostRepository(private val dao: PostDao) {

    fun getAllPosts(): Flow<List<PostEntity>> {
        return dao.getAllPosts()
    }

    fun getPostById(id: Int): Flow<PostEntity?> {
        return dao.getPostById(id)
    }

    suspend fun insertPost(post: PostEntity) {
        dao.insert(post)
    }

    suspend fun deletePost(post: PostEntity) {
        dao.delete(post)
    }
}
