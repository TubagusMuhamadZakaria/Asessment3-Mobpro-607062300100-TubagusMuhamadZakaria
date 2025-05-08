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
    onCancel: () -> Unit
) {
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var bahan by remember { mutableStateOf("") }
    var langkah by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Tambah Resep Baru", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul Resep") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        OutlinedTextField(
            value = bahan,
            onValueChange = { bahan = it },
            label = { Text("Bahan") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        OutlinedTextField(
            value = langkah,
            onValueChange = { langkah = it },
            label = { Text("Langkah") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onCancel) {
                Text("Batal")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
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
            }) {
                Text("Simpan")
            }
        }
    }
}
