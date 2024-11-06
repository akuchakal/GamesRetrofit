package com.hackerlopers.gamesretrofit.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel
import com.hackerlopers.gamesretrofit.views.DetailView
import com.hackerlopers.gamesretrofit.views.HomeView
import com.hackerlopers.gamesretrofit.views.SearchGameView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            val viewModel = hiltViewModel<GamesViewModel>()
            HomeView(viewModel, navController)
        }
        composable(
            "DetailView/{id}",
            arguments = listOf(
                navArgument("id") {type = NavType.IntType}
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            val viewModel = hiltViewModel<GamesViewModel>()
            DetailView(viewModel, navController, id)
        }

        composable("SearchGameView") {
            val viewModel = hiltViewModel<GamesViewModel>()
            SearchGameView(viewModel, navController)
        }
    }
}