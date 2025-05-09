package com.tubagus0100.resepku.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.ResepViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: ResepViewModel
) {
    var query by remember { mutableStateOf("") }
    val selectedResepIds = remember { mutableStateListOf<Int>() }
    val context = LocalContext.current
    var isGridMode by remember { mutableStateOf(false) }

    val shareTitle = stringResource(R.string.share_title)
    val recipeNameLabel = stringResource(R.string.recipe_name)
    val recipeDescLabel = stringResource(R.string.recipe_desc)

    val resepList by viewModel.resepList.collectAsState(initial = emptyList())
    val filteredResep = resepList.filter {
        it.judul.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_home)) },
                actions = {
                    IconButton(onClick = { isGridMode = !isGridMode }) {
                        Icon(
                            imageVector = if (isGridMode) Icons.Default.List else Icons.Default.ViewModule,
                            contentDescription = if (isGridMode) "Tampilan List" else "Tampilan Grid"
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (selectedResepIds.isNotEmpty()) {
                Button(
                    onClick = {
                        val selectedRecipes = resepList.filter { it.id in selectedResepIds }
                        val shareText = selectedRecipes.joinToString("\n\n") {
                            "$recipeNameLabel: ${it.judul}\n$recipeDescLabel: ${it.deskripsi}"
                        }

                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "$shareTitle\n$shareText")
                            type = "text/plain"
                        }

                        context.startActivity(Intent.createChooser(intent, shareTitle))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(stringResource(R.string.share_button, selectedResepIds.size))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Resep"
                )
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
                label = { Text(stringResource(R.string.search_placeholder)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            if (filteredResep.isEmpty()) {
                Text(
                    text = stringResource(R.string.not_found_message),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                if (isGridMode) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(filteredResep) { resep ->
                            ItemResep(
                                resep = resep,
                                onItemClick = { onRecipeClick(resep.id) },
                                isSelected = resep.id in selectedResepIds,
                                onCheckedChange = { checked ->
                                    if (checked) selectedResepIds.add(resep.id)
                                    else selectedResepIds.remove(resep.id)
                                },
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                } else {
                    LazyColumn {
                        items(filteredResep) { resep ->
                            ItemResep(
                                resep = resep,
                                onItemClick = { onRecipeClick(resep.id) },
                                isSelected = resep.id in selectedResepIds,
                                onCheckedChange = { checked ->
                                    if (checked) selectedResepIds.add(resep.id)
                                    else selectedResepIds.remove(resep.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
