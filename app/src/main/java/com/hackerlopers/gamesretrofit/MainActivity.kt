package com.hackerlopers.gamesretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hackerlopers.gamesretrofit.navigation.NavManager
import com.hackerlopers.gamesretrofit.ui.theme.GamesRetrofitTheme
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamesRetrofitTheme {
                NavManager()
            }
        }
    }
}