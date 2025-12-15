package com.crypto.cryptoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crypto.cryptoapp.ui.detail.CoinDetailScreen
import com.crypto.cryptoapp.ui.list.CoinListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            CoinListScreen { coinId ->
                navController.navigate("detail/$coinId")
            }
        }
        composable("detail/{id}") { backStackEntry ->
            CoinDetailScreen(
                coinId = backStackEntry.arguments?.getString("id") ?: ""
            )
        }
    }
}
