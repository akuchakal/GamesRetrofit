package com.hackerlopers.gamesretrofit.views

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
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val games by viewModel.games.collectAsState()
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(games) { item ->
                Text(text = item.name)
            }
        }
    }
}