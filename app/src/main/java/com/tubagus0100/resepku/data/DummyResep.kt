package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.model.Resep

object DummyResep {
    val listResep = listOf(
        Resep(
            id = "nasi_goreng",
            nama = "Nasi Goreng",
            deskripsi = "Nasi goreng dengan bumbu khas Indonesia.",
            gambarResep = R.drawable.nasi_goreng
        ),
        Resep(
            id = "mie_goreng",
            nama = "Mie Goreng",
            deskripsi = "Mie goreng pedas gurih.",
            gambarResep = R.drawable.mie_goreng
        )
    )
}
