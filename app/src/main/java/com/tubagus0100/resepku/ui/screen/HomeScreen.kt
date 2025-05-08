package com.tubagus0100.resepku.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.ui.ResepViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit,
    onAddClick: () -> Unit,
    viewModel: ResepViewModel
) {
    var query by remember { mutableStateOf("") }
    val selectedResepIds = remember { mutableStateListOf<Int>() }
    val context = LocalContext.current

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
                title = { Text(stringResource(R.string.title_home)) }
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
                LazyColumn {
                    items(filteredResep) { resep ->
                        ItemResep(
                            resep = resep,
                            onItemClick = { onRecipeClick(resep.id.toString()) },
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

}
