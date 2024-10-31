package com.hackerlopers.gamesretrofit.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackerlopers.gamesretrofit.model.GameList
import com.hackerlopers.gamesretrofit.repository.GamesRepository
import com.hackerlopers.gamesretrofit.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GamesViewModel @Inject constructor(private val repository: GamesRepository) : ViewModel() {
    private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    var state by mutableStateOf(GameState())
        private set

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getGames()
                _games.value = result
            }
        }
    }

    fun getGameById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getGameById(id)
                state = state.copy(
                    name = result?.name ?: state.name,
                    descriptionRaw = result?.descriptionRaw ?: state.descriptionRaw,
                    metacritic = result?.metacritic ?: 111,
                    website = result?.website ?: state.website,
                    backgroundImage = result?.backgroundImage ?: state.backgroundImage
                )
            }
        }
    }

    fun clean() {
        state = GameState()
    }
}