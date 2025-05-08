package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@Composable
fun FormResepScreen(
    resep: ResepEntity? = null,
    onSave: (ResepEntity) -> Unit,
    onCancel: () -> Unit
) {
    var judul by remember { mutableStateOf(resep?.judul ?: "") }
    var deskripsi by remember { mutableStateOf(resep?.deskripsi ?: "") }
    var bahan by remember { mutableStateOf(resep?.bahan ?: "") }
    var langkah by remember { mutableStateOf(resep?.langkah ?: "") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = judul, onValueChange = { judul = it }, label = { Text("Judul") })
        OutlinedTextField(value = deskripsi, onValueChange = { deskripsi = it }, label = { Text("Deskripsi") })
        OutlinedTextField(value = bahan, onValueChange = { bahan = it }, label = { Text("Bahan") })
        OutlinedTextField(value = langkah, onValueChange = { langkah = it }, label = { Text("Langkah") })

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val updated = resep?.copy(
                judul = judul,
                deskripsi = deskripsi,
                bahan = bahan,
                langkah = langkah
            ) ?: ResepEntity(
                judul = judul,
                deskripsi = deskripsi,
                bahan = bahan,
                langkah = langkah
            )
            onSave(updated)
        }) {
            Text("Simpan")
        }

        TextButton(onClick = onCancel) {
            Text("Batal")
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
