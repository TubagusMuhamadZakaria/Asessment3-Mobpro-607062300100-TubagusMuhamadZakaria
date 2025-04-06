package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model.Resep

@Composable
fun DetailScreen(resep: Resep) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = resep.nama, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = resep.gambarResep),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Deskripsi:", style = MaterialTheme.typography.titleMedium)
        Text(resep.deskripsi)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Bahan:", style = MaterialTheme.typography.titleMedium)
        for (bahan in resep.bahan) {
            Text("- $bahan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Langkah-langkah:", style = MaterialTheme.typography.titleMedium)
        for ((i, langkah) in resep.langkah.withIndex()) {
            Text("${i + 1}. $langkah")
        }
    }
}
