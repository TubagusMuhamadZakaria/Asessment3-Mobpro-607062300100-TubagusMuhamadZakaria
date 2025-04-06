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
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model.Resep
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(resep: Resep) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(resep.nama) })
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
