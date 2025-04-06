package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.model.Resep

object DummyResep {
    val listResep = listOf(
        Resep(
            id = "nasi_goreng",
            nama = "Nasi Goreng",
            deskripsi = "Nasi goreng dengan bumbu khas Indonesia.",
            gambarResep = R.drawable.nasi_goreng,
            bahan = listOf(
                "2 piring nasi putih",
                "2 siung bawang putih",
                "1 butir telur",
                "Kecap manis, garam, dan merica"
            ),
            langkah = listOf(
                "Tumis bawang putih sampai harum",
                "Masukkan telur, orak-arik",
                "Masukkan nasi, aduk rata",
                "Tambahkan kecap dan bumbu, aduk hingga matang"
            )
        ),

        Resep(
            id = "mie_goreng",
            nama = "Mie Goreng",
            deskripsi = "Mie goreng pedas gurih.",
            gambarResep = R.drawable.mie_goreng,
            bahan = listOf(
                "1 bungkus mie instan",
                "1 siung bawang putih",
                "1 butir telur",
                "Sawi dan cabai sesuai selera"
            ),
            langkah = listOf(
                "Rebus mie hingga setengah matang, tiriskan",
                "Tumis bawang putih, masukkan telur, orak-arik",
                "Masukkan mie, tambahkan bumbu dan sayuran",
                "Aduk hingga matang dan bumbu merata"
            )
        )
    )
}
