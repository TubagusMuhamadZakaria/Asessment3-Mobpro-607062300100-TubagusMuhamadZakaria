package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model.Resep
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(resep: Resep, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(resep.nama) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = resep.gambarResep),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Deskripsi:", style = MaterialTheme.typography.titleMedium)
            Text(resep.deskripsi, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Bahan:", style = MaterialTheme.typography.titleMedium)
            resep.bahan.forEach { bahan ->
                Text("- $bahan", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Langkah-langkah:", style = MaterialTheme.typography.titleMedium)
            resep.langkah.forEachIndexed { index, langkah ->
                Text("${index + 1}. $langkah", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val contohResep = Resep(
        id = "soto_ayam",
        nama = "Soto Ayam",
        deskripsi = "Soto ayam khas Jawa dengan kuah kuning.",
        gambarResep = R.drawable.soto_ayam, // Pakai gambar yang sudah ada
        bahan = listOf("Daging ayam", "Bumbu halus", "Soun", "Telur rebus"),
        langkah = listOf("Rebus ayam", "Tumis bumbu", "Campurkan dan masak hingga matang")
    )

    ResepkuTheme {
        DetailScreen(resep = contohResep, onBackClick = {})
    }
}
