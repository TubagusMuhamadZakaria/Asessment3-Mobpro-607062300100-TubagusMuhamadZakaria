package com.tubagus0100.resepku.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.R
import com.tubagus0100.resepku.data.ThemeSetting
import com.tubagus0100.resepku.model.Post
import com.tubagus0100.resepku.ui.ResepViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: ResepViewModel,
    isGridMode: Boolean,
    onToggleView: (Boolean) -> Unit,
    @Suppress("UNUSED_PARAMETER") currentTheme: ThemeSetting,
    onToggleTheme: () -> Unit,
    onLogout: () -> Unit,
    onProfileClick: () -> Unit,
    onAddPostClick: () -> Unit,
    postList: List<Post>
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
                title = { Text(stringResource(R.string.title_home)) },
                actions = {
                    IconButton(onClick = { onToggleView(!isGridMode) }) {
                        Icon(
                            imageVector = if (isGridMode) Icons.AutoMirrored.Filled.List else Icons.Default.GridView,
                            contentDescription = "Toggle View"
                        )
                    }
                    IconButton(onClick = { onToggleTheme() }) {
                        Icon(Icons.Default.Brightness6, contentDescription = "Toggle Theme")
                    }
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.Person, contentDescription = "Profil")
                    }
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
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
            Column {
                FloatingActionButton(
                    onClick = onAddClick,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah Resep")
                }
                FloatingActionButton(onClick = onAddPostClick) {
                    Text("Post")
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(bottom = 88.dp)
                    ) {
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
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 88.dp)
                    ) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Post dari Pengguna",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn {
                items(postList) { post ->
                    Text(text = post.title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = post.body, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
