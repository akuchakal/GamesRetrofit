package com.hackerlopers.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hackerlopers.gamesretrofit.components.CardGame
import com.hackerlopers.gamesretrofit.components.MainTopBar
import com.hackerlopers.gamesretrofit.ui.theme.CustomBlack
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CustomBlack,
        topBar = {
            MainTopBar(
                title = "API Games",
                onClickBakButton = {},
                onClickAction = { navController.navigate("SearchGameView") })
        }
    ) { innerPadding ->
        ContentHomeView(viewModel, Modifier.padding(innerPadding), navController)
    }
}


@Composable
fun ContentHomeView(viewModel: GamesViewModel, modifier: Modifier, navController: NavController) {
    val games by viewModel.games.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(games) { item ->
            CardGame(game = item, onClick = { navController.navigate("DetailView/${item.id}") })
            Text(
                text = item.name,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

    }
}