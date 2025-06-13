package com.tubagus0100.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tubagus0100.resepku.data.Injection
import com.tubagus0100.resepku.data.PreferenceManager
import com.tubagus0100.resepku.data.ThemeSetting
import com.tubagus0100.resepku.model.Post
import com.tubagus0100.resepku.ui.*
import com.tubagus0100.resepku.ui.screen.*
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import com.tubagus0100.resepku.viewmodel.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferenceManager = PreferenceManager.getInstance(this)

        setContent {
            val themeSetting by preferenceManager.themeSetting.collectAsState(initial = ThemeSetting.LIGHT)
            ResepkuTheme(darkTheme = themeSetting == ThemeSetting.DARK) {
                ResepkuApp(preferenceManager, themeSetting)
            }
        }
    }
}

@Composable
fun ResepkuApp(
    preferenceManager: PreferenceManager,
    themeSetting: ThemeSetting
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isLoggedIn by preferenceManager.isLoggedIn.collectAsState(initial = false)
    val isGridMode by preferenceManager.isGridMode.collectAsState(initial = false)

    val resepViewModel: ResepViewModel = viewModel(
        factory = ResepViewModelFactory(
            Injection.provideRepository(context),
            preferenceManager
        )
    )

    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(Injection.provideUserRepository())
    )

    val localPostViewModel: LocalPostViewModel = viewModel(
        factory = LocalPostViewModelFactory(Injection.provideLocalPostRepository(context))
    )

    val postList by localPostViewModel.posts.collectAsState()
    val mappedPosts = postList.map { Post(it.id, it.title, it.body) }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    scope.launch {
                        preferenceManager.setLoggedIn(true)
                    }
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                onRecipeClick = { id ->
                    navController.navigate("detail/$id")
                },
                onAddClick = {
                    navController.navigate("form?resepId=-1")
                },
                viewModel = resepViewModel,
                isGridMode = isGridMode,
                onToggleView = { newValue ->
                    scope.launch {
                        preferenceManager.setGridMode(newValue)
                    }
                },
                currentTheme = themeSetting,
                onToggleTheme = {
                    scope.launch {
                        val nextTheme = if (themeSetting == ThemeSetting.LIGHT) ThemeSetting.DARK else ThemeSetting.LIGHT
                        preferenceManager.setThemeSetting(nextTheme)
                    }
                },
                onLogout = {
                    scope.launch {
                        preferenceManager.setLoggedIn(false)
                    }
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onProfileClick = {
                    navController.navigate("profile")
                },
                onAddPostClick = {
                    navController.navigate("addpost")
                },
                postList = mappedPosts,
                onDeletePost = { post ->
                    localPostViewModel.deletePost(
                        com.tubagus0100.resepku.model1.PostEntity(
                            id = post.id,
                            title = post.title,
                            body = post.body
                        )
                    )
                },
                isPostLoading = localPostViewModel.isLoading.collectAsState().value
            )
        }

        composable(
            route = "detail/{resepId}",
            arguments = listOf(navArgument("resepId") { type = NavType.IntType })
        ) { backStackEntry ->
            val resepId = backStackEntry.arguments?.getInt("resepId") ?: -1
            val resep = resepViewModel.getResepById(resepId).collectAsState(initial = null).value

            resep?.let {
                DetailScreen(
                    resep = it,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = {
                        navController.navigate("form?resepId=${resep.id}")
                    },
                    onDeleteClick = {
                        resepViewModel.deleteResep(it)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = "form?resepId={resepId}",
            arguments = listOf(
                navArgument("resepId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val resepId = backStackEntry.arguments?.getInt("resepId") ?: -1
            val resep = resepViewModel.getResepById(resepId).collectAsState(initial = null).value

            FormResepScreen(
                resep = resep,
                onSave = { entity ->
                    if (resepId == -1) {
                        resepViewModel.insertResep(entity)
                    } else {
                        resepViewModel.updateResep(entity)
                    }
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable("userlist") {
            UserListScreen(viewModel = userViewModel)
        }

        composable("profile") {
            ProfileScreen(onBack = { navController.popBackStack() })
        }

        composable("addpost") {
            AddPostScreen(
                postViewModel = localPostViewModel,
                onPostSuccess = {
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "editpost/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: -1
            val post = localPostViewModel.posts.collectAsState().value.find { it.id == postId }

            if (post != null) {
                EditPostScreen(
                    post = post,
                    postViewModel = localPostViewModel,
                    onSuccess = { navController.popBackStack() },
                    onCancel = { navController.popBackStack() }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ResepkuAppPreview() {
    val dummyPref = PreferenceManager.getInstance(LocalContext.current)
    ResepkuTheme {
        ResepkuApp(dummyPref, ThemeSetting.LIGHT)
    }
}
