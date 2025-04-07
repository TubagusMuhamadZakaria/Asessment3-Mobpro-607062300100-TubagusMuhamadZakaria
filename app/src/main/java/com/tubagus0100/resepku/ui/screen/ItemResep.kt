package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.model.Resep
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import androidx.compose.material3.Checkbox

@Composable
fun ItemResep(
    resep: Resep,
    onItemClick: (String) -> Unit,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(resep.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = resep.gambarResep),
                contentDescription = resep.nama,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = resep.nama,
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
    ResepkuTheme {
        ItemResep(
            resep = DummyResep.listResep.first(),
            onItemClick = {},
            isSelected = false,
            onCheckedChange = {}
        )
    }
}

