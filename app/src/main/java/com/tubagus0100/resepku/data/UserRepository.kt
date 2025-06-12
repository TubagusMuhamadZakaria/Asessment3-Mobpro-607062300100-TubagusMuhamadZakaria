package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.network.ApiService
import com.tubagus0100.resepku.network.UserResponseItem

class UserRepository(private val api: ApiService) {
    suspend fun getAllUsers(): List<UserResponseItem> = api.getUsers()
}
