package com.tubagus0100.resepku.network

import com.tubagus0100.resepku.model.Post
import com.tubagus0100.resepku.model.PostRequest
import com.tubagus0100.resepku.model.PostResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponseItem>

    @GET("users")
    suspend fun getUsers(): List<UserResponseItem>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @POST("posts")
    suspend fun createPost(@Body post: PostRequest): retrofit2.Response<PostRequest>

}
