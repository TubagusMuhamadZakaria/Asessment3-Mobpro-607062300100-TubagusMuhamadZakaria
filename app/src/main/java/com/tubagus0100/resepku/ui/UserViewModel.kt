package com.tubagus0100.resepku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tubagus0100.resepku.data.UserRepository
import com.tubagus0100.resepku.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = repository.getAllUsers()
                val mapped = response.map {
                    User(
                        id = it.id,
                        name = it.name,
                        email = it.email,
                        avatar = "https://i.pravatar.cc/150?u=${it.id}"
                    )
                }
                _users.value = mapped
            } catch (e: Exception) {
                _users.value = emptyList()
            }
        }
    }
}