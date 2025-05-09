package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import androidx.compose.runtime.saveable.rememberSaveable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormResepScreen(
    resep: ResepEntity? = null,
    onSave: (ResepEntity) -> Unit,
    onCancel: () -> Unit
) {
    var judul by rememberSaveable { mutableStateOf("") }
    var deskripsi by rememberSaveable { mutableStateOf("") }
    var bahan by rememberSaveable { mutableStateOf("") }
    var langkah by rememberSaveable { mutableStateOf("") }

    // Set value awal saat resep tersedia (mode edit)
    LaunchedEffect(resep) {
        resep?.let {
            judul = it.judul
            deskripsi = it.deskripsi
            bahan = it.bahan
            langkah = it.langkah
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (resep == null) "Tambah Resep" else "Edit Resep") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
                label = { Text("Bahan (pisahkan dengan ; )") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = langkah,
                onValueChange = { langkah = it },
                label = { Text("Langkah (pisahkan dengan ; )") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        if (judul.isNotBlank() && deskripsi.isNotBlank()) {
                            onSave(
                                ResepEntity(
                                    id = resep?.id ?: 0,
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

                OutlinedButton(onClick = onCancel) {
                    Text("Batal")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormResepScreenPreview() {
    ResepkuTheme {
        FormResepScreen(
            onSave = {},
            onCancel = {}
        )
    }
}
