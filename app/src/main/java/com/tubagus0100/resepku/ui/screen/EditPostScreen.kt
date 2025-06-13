package com.tubagus0100.resepku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tubagus0100.resepku.model1.PostEntity
import com.tubagus0100.resepku.ui.LocalPostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostScreen(
    post: PostEntity,
    postViewModel: LocalPostViewModel,
    onSuccess: () -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(post.title) }
    var body by remember { mutableStateOf(post.body) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Postingan") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Isi Post") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onCancel) {
                    Text("Batal")
                }
                Button(onClick = {
                    postViewModel.updatePost(
                        post.copy(title = title, body = body)
                    ) {
                        onSuccess()
                    }
                }) {
                    Text("Simpan Perubahan")
                }
            }
        }
    }
}
