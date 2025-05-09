package com.tubagus0100.resepku.data

import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.model1.Resep

@Suppress("unused")
object DummyResep {
    val listResep: List<Resep> = listOf(
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
        ),
        Resep(
            id = "sate_ayam",
            nama = "Sate Ayam",
            deskripsi = "Sate ayam bumbu kacang khas Madura.",
            gambarResep = R.drawable.sate_ayam,
            bahan = listOf(
                "500g daging ayam",
                "Tusuk sate",
                "Bumbu kacang",
                "Kecap manis"
            ),
            langkah = listOf(
                "Potong ayam dan tusuk ke tusukan sate",
                "Bakar sate di atas bara api",
                "Olesi dengan bumbu kacang dan kecap",
                "Bakar kembali hingga matang"
            )
        ),
        Resep(
            id = "rendang",
            nama = "Rendang Daging",
            deskripsi = "Rendang khas Padang dengan daging sapi empuk.",
            gambarResep = R.drawable.rendang,
            bahan = listOf(
                "1 kg daging sapi",
                "1 liter santan",
                "Bumbu rendang (cabai, bawang, rempah-rempah)"
            ),
            langkah = listOf(
                "Tumis bumbu rendang hingga harum",
                "Masukkan daging dan aduk rata",
                "Tuang santan, masak hingga mengental",
                "Masak terus hingga rendang kering dan matang"
            )
        ),
        Resep(
            id = "ayam_goreng",
            nama = "Ayam Goreng",
            deskripsi = "Ayam goreng renyah dengan bumbu kuning.",
            gambarResep = R.drawable.ayam_goreng,
            bahan = listOf(
                "1 ekor ayam",
                "Bumbu kuning (kunyit, bawang putih, garam)",
                "Minyak goreng"
            ),
            langkah = listOf(
                "Lumuri ayam dengan bumbu kuning",
                "Rebus hingga bumbu meresap",
                "Goreng ayam hingga kecokelatan dan renyah"
            )
        ),
        Resep(
            id = "gado_gado",
            nama = "Gado-Gado",
            deskripsi = "Salad sayuran dengan bumbu kacang khas Indonesia.",
            gambarResep = R.drawable.gado_gado,
            bahan = listOf(
                "Sayuran rebus (kol, tauge, kacang panjang)",
                "Telur rebus",
                "Bumbu kacang",
                "Kerupuk"
            ),
            langkah = listOf(
                "Siapkan sayuran rebus dan telur",
                "Tata di piring",
                "Siram dengan bumbu kacang",
                "Tambahkan kerupuk di atasnya"
            )
        ),
        Resep(
            id = "bakso",
            nama = "Bakso",
            deskripsi = "Bakso daging sapi dengan kuah kaldu gurih.",
            gambarResep = R.drawable.bakso,
            bahan = listOf(
                "500g daging sapi giling",
                "100g tepung tapioka",
                "Bawang putih, garam, merica",
                "Kaldu sapi"
            ),
            langkah = listOf(
                "Campur daging dan bumbu hingga kalis",
                "Bentuk bulat dan rebus hingga mengapung",
                "Masak kuah kaldu",
                "Sajikan bakso dengan kuah"
            )
        ),
        Resep(
            id = "nasi_uduk",
            nama = "Nasi Uduk",
            deskripsi = "Nasi gurih dengan santan, khas Betawi.",
            gambarResep = R.drawable.nasi_uduk,
            bahan = listOf(
                "2 gelas beras",
                "300 ml santan",
                "Daun salam, serai, garam"
            ),
            langkah = listOf(
                "Masak beras dengan santan dan rempah",
                "Kukus hingga matang",
                "Sajikan dengan lauk pelengkap"
            )
        )
    )
}
