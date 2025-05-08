package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormResepScreen(
    onSave: (ResepEntity) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var bahan by remember { mutableStateOf("") }
    var langkah by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Tambah Resep", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bahan,
            onValueChange = { bahan = it },
            label = { Text("Bahan") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = langkah,
            onValueChange = { langkah = it },
            label = { Text("Langkah") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = onCancel) {
                Text("Batal")
            }
            Button(
                onClick = {
                    if (judul.isNotBlank() && deskripsi.isNotBlank()) {
                        onSave(
                            ResepEntity(
                                judul = judul,
                                deskripsi = deskripsi,
                                bahan = bahan,
                                langkah = langkah
                            )
                        )
                    }
                }
            ) {
                Text("Simpan")
            }
        }
    }
}
