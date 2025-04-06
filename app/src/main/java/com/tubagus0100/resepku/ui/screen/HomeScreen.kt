package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.model.Resep
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onRecipeClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Resep Makanan Favorit")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(DummyResep.listResep) { resep ->
                ResepItem(resep = resep, onClick = { onRecipeClick(resep.id) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ResepItem(resep: Resep, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = resep.gambarResep),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = resep.nama, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = resep.deskripsi, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ResepkuTheme {
        HomeScreen(onRecipeClick = {})
    }
}


