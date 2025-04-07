package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import androidx.compose.ui.Alignment

@Composable
fun InputResepScreen(
    onSaveClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var nama by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tambahkan Resep Baru",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Resep") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi Resep") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { onSaveClick(nama, deskripsi) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Simpan")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputResepScreenPreview() {
    ResepkuTheme {
        InputResepScreen(
            onSaveClick = { _, _ -> }
        )
    }
}
