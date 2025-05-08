package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@Composable
fun ItemResep(
    resep: ResepEntity,
    onItemClick: (String) -> Unit,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(resep.id.toString()) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = resep.judul,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = resep.deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
            }
            Checkbox(
                checked = isSelected,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemResepPreview() {
    val dummyResep = ResepEntity(
        id = 1,
        judul = "Nasi Goreng",
        deskripsi = "Nasi goreng enak dan praktis",
        bahan = "Nasi, telur, kecap",
        langkah = "1. Tumis bawang\n2. Masukkan nasi\n3. Tambahkan bumbu"
    )
    ResepkuTheme {
        ItemResep(
            resep = dummyResep,
            onItemClick = {},
            isSelected = false,
            onCheckedChange = {}
        )
    }
}
