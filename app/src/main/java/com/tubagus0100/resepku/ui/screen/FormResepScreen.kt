package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity

@Composable
fun FormResepScreen(
    onSave: (ResepEntity) -> Unit,
    onCancel: () -> Unit
) {
    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var bahan by remember { mutableStateOf("") }
    var langkah by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tambah Resep Baru", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

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
            label = { Text("Bahan (pisahkan dengan koma)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = langkah,
            onValueChange = { langkah = it },
            label = { Text("Langkah-langkah (pisahkan dengan koma)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onCancel) {
                Text("Batal")
            }
            Spacer(modifier = Modifier.width(8.dp))
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
