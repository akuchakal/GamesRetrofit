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
import com.hackerlopers.components.CardGame
import com.hackerlopers.components.MainTopBar
import com.hackerlopers.gamesretrofit.ui.theme.CustomBlack
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(title = "API Games") {}
        }
    ) { innerPadding ->
        ContentHomeView(viewModel, Modifier.padding(innerPadding))
    }
}


@Composable
fun ContentHomeView(viewModel: GamesViewModel, modifier: Modifier) {
    val games by viewModel.games.collectAsState()
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .background(color = CustomBlack)) {
        items(games) { item ->
            CardGame(game = item, onClick = { })
            Text(
                text = item.name,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

    }
}