package com.tubagus0100.resepku.model

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val imageUri: String? = null
)
