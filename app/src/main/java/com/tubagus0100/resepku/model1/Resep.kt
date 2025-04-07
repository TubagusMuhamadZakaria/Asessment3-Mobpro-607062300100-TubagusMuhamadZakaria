package com.tubagus0100.resepku.model1

data class Resep(
    val id: String,
    val nama: String,
    val deskripsi: String,
    val gambarResep: Int,
    val bahan: List<String>,
    val langkah: List<String>,
    var isSelected: Boolean = false
)
