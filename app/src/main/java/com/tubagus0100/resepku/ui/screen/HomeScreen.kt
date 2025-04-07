package com.tubagus0100.resepku.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val selectedResepIds = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    // Ambil string di luar onClick
    val shareTitle = stringResource(R.string.share_title)
    val recipeNameLabel = stringResource(R.string.recipe_name)
    val recipeDescLabel = stringResource(R.string.recipe_desc)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_home)) }
            )
        },
        bottomBar = {
            if (selectedResepIds.isNotEmpty()) {
                Button(
                    onClick = {
                        val selectedRecipes = DummyResep.listResep.filter { it.id in selectedResepIds }
                        val shareText = selectedRecipes.joinToString("\n\n") {
                            "$recipeNameLabel: ${it.nama}\n$recipeDescLabel: ${it.deskripsi}"
                        }

                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "$shareTitle\n$shareText")
                            type = "text/plain"
                        }

                        context.startActivity(
                            Intent.createChooser(intent, shareTitle)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(stringResource(R.string.share_button, selectedResepIds.size))
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
                label = { Text(stringResource(R.string.search_placeholder)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            val filteredResep = DummyResep.listResep.filter {
                it.nama.contains(query, ignoreCase = true)
            }

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
