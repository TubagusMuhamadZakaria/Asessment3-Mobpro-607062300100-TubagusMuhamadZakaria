package com.tubagus0100.resepku.data

import androidx.room.*
import com.tubagus0100.resepku.model1.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY id DESC")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity)

    @Delete
    suspend fun delete(post: PostEntity)
}
