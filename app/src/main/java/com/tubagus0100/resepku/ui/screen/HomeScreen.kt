package com.tubagus0100.resepku.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.model.Resep
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val selectedResepIds = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resep Makanan Favorit") }
            )
        },
        bottomBar = {
            if (selectedResepIds.isNotEmpty()) {
                Button(
                    onClick = {
                        val selectedRecipes = DummyResep.listResep.filter { it.id in selectedResepIds }
                        val shareText = selectedRecipes.joinToString("\n\n") {
                            "Nama: ${it.nama}\nDeskripsi: ${it.deskripsi}"
                        }

                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Resep yang saya pilih:\n$shareText")
                            type = "text/plain"
                        }

                        context.startActivity(
                            Intent.createChooser(intent, "Bagikan Resep via")
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Bagikan (${selectedResepIds.size}) Resep")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Cari Resep...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            val filteredResep = DummyResep.listResep.filter {
                it.nama.contains(query, ignoreCase = true)
            }

            LazyColumn {
                items(filteredResep) { resep ->
                    ItemResep(
                        resep = resep,
                        onItemClick = { onRecipeClick(it) },
                        isSelected = resep.id in selectedResepIds,
                        onCheckedChange = { checked ->
                            if (checked) {
                                selectedResepIds.add(resep.id)
                            } else {
                                selectedResepIds.remove(resep.id)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ResepkuTheme {
        HomeScreen(
            onRecipeClick = {}
        )
    }
}
