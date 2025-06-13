package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.network.ApiService
import com.tubagus0100.resepku.network.UserResponseItem

class UserRepository(private val apiService: ApiService) {
    suspend fun getAllUsers(): List<UserResponseItem> = apiService.getUsers()
}