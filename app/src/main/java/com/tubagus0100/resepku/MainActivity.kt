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
import com.tubagus0100.resepku.ui.ResepViewModel
import com.tubagus0100.resepku.ui.screen.*
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import com.tubagus0100.resepku.viewmodel.ResepViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResepkuTheme {
                ResepkuApp()
            }
        }
    }
}

@Composable
fun ResepkuApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val preferenceManager = PreferenceManager.getInstance(context)

    val viewModel: ResepViewModel = viewModel(
        factory = ResepViewModelFactory(
            Injection.provideRepository(context),
            preferenceManager
        )
    )

    val isGridMode by preferenceManager.isGridMode.collectAsState(initial = false)

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen(
                onRecipeClick = { id ->
                    navController.navigate("detail/$id")
                },
                onAddClick = {
                    navController.navigate("form?resepId=-1")
                },
                viewModel = viewModel,
                isGridMode = isGridMode,
                onToggleView = { newValue ->
                    scope.launch {
                        preferenceManager.setGridMode(newValue)
                    }
                }
            )
        }

        composable(
            route = "detail/{resepId}",
            arguments = listOf(navArgument("resepId") { type = NavType.IntType })
        ) { backStackEntry ->
            val resepId = backStackEntry.arguments?.getInt("resepId") ?: -1
            val resep = viewModel.getResepById(resepId).collectAsState(initial = null).value

            resep?.let {
                DetailScreen(
                    resep = it,
                    onBackClick = { navController.popBackStack() },
                    onEditClick = {
                        navController.navigate("form?resepId=${resep.id}")
                    },
                    onDeleteClick = {
                        viewModel.deleteResep(it)
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
            val resep = viewModel.getResepById(resepId).collectAsState(initial = null).value

            FormResepScreen(
                resep = resep,
                onSave = { entity ->
                    if (resepId == -1) {
                        viewModel.insertResep(entity)
                    } else {
                        viewModel.updateResep(entity)
                    }
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResepkuAppPreview() {
    ResepkuTheme {
        ResepkuApp()
    }
}
