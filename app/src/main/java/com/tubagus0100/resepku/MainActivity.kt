package com.tubagus0100.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tubagus0100.resepku.data.DummyResep
import com.tubagus0100.resepku.ui.screen.DetailScreen
import com.tubagus0100.resepku.ui.screen.HomeScreen
import com.tubagus0100.resepku.ui.screen.SplashScreen
import com.tubagus0100.resepku.ui.theme.ResepkuTheme

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
    }
}

@Preview(showBackground = true)
@Composable
fun ResepkuAppPreview() {
    ResepkuTheme {
        ResepkuApp()
    }
}
