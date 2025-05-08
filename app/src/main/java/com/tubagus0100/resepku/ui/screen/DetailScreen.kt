package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(resep: ResepEntity, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(resep.judul) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            horizontalAlignment = Alignment.Start
        ) {
            Text("Deskripsi:", style = MaterialTheme.typography.titleMedium)
            Text(resep.deskripsi, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Bahan:", style = MaterialTheme.typography.titleMedium)
            resep.bahan.split(";").forEach { bahan ->
                Text("- $bahan", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Langkah-langkah:", style = MaterialTheme.typography.titleMedium)
            resep.langkah.split(";").forEachIndexed { index, langkah ->
                Text("${index + 1}. $langkah", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val contohResep = ResepEntity(
        id = 1,
        judul = "Soto Ayam",
        deskripsi = "Soto ayam khas Jawa dengan kuah kuning.",
        bahan = "Daging ayam;Bumbu halus;Soun;Telur rebus",
        langkah = "Rebus ayam;Tumis bumbu;Campurkan dan masak hingga matang"
    )

    ResepkuTheme {
        DetailScreen(resep = contohResep, onBackClick = {})
    }
}
