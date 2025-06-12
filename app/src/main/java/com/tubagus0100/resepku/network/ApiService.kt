package com.tubagus0100.resepku.network

import com.tubagus0100.resepku.model.PostResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponseItem>

    @GET("users")
    suspend fun getUsers(): List<UserResponseItem>
}
