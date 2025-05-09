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
import com.tubagus0100.resepku.ui.ResepViewModel
import com.tubagus0100.resepku.ui.screen.*
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import com.tubagus0100.resepku.viewmodel.ResepViewModelFactory
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
                },
                currentTheme = themeSetting,
                onToggleTheme = {
                    scope.launch {
                        val nextTheme = if (themeSetting == ThemeSetting.LIGHT) ThemeSetting.DARK else ThemeSetting.LIGHT
                        preferenceManager.setThemeSetting(nextTheme)
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
    val dummyPref = PreferenceManager.getInstance(LocalContext.current)
    ResepkuTheme {
        ResepkuApp(dummyPref, ThemeSetting.LIGHT)
    }
}
