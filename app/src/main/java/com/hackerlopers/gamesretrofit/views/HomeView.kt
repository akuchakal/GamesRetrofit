package com.hackerlopers.gamesretrofit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.hackerlopers.gamesretrofit.components.CardGame
import com.hackerlopers.gamesretrofit.components.Loader
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
//    val games by viewModel.games.collectAsState()
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(gamesPage.itemCount) { index ->
            val gameItem = gamesPage[index]
            if (gameItem != null) {
                CardGame(game = gameItem, onClick = { navController.navigate("DetailView/${gameItem.id}") })
                Text(
                    text = gameItem.name,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
        when (gamesPage.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Loader()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    Text(text = "Error al cargar...")
                }
            }
        }
    }
}