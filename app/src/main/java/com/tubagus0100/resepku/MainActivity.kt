package com.tubagus0100.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.data.Injection
import com.tubagus0100.resepku.model1.ResepEntity
import com.tubagus0100.resepku.ui.ResepViewModel
import com.tubagus0100.resepku.ui.screen.*
import com.tubagus0100.resepku.ui.theme.ResepkuTheme
import com.tubagus0100.resepku.viewmodel.ResepViewModelFactory

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
    val viewModel: ResepViewModel = viewModel(
        factory = ResepViewModelFactory(Injection.provideRepository(context))
    )

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
                    navController.navigate("form")
                }
            )
        }

        composable(
            route = "detail/{resepId}",
            arguments = listOf(navArgument("resepId") { type = NavType.StringType })
        ) { backStackEntry ->
            val resepId = backStackEntry.arguments?.getString("resepId")
            val resep = DummyResep.listResep.find { it.id == resepId }
            if (resep != null) {
                DetailScreen(
                    resep = resep,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable("form") {
            FormResepScreen(
                onSave = { resep: ResepEntity ->
                    viewModel.insertResep(resep)
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
