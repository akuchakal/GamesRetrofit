package com.hackerlopers.gamesretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hackerlopers.gamesretrofit.ui.theme.GamesRetrofitTheme
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel
import com.hackerlopers.gamesretrofit.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val gamesViewModel: GamesViewModel by viewModels()
        setContent {
            GamesRetrofitTheme {
                HomeView(viewModel = gamesViewModel)
            }
        }
    }
}