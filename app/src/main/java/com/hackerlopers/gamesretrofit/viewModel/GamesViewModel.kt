package com.hackerlopers.gamesretrofit.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.hackerlopers.gamesretrofit.data.GamesDataSource
import com.hackerlopers.gamesretrofit.model.GameList
import com.hackerlopers.gamesretrofit.repository.GamesRepository
import com.hackerlopers.gamesretrofit.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class GamesViewModel @Inject constructor(private val repository: GamesRepository) : ViewModel() {
    private val _games = MutableStateFlow<List<GameList>>(emptyList())
    val games = _games.asStateFlow()

    var state by mutableStateOf(GameState())
        private set

    private var apiJob: Job? = null

    private val _searchFlow = MutableStateFlow("")
    val searchFlow = _searchFlow.asStateFlow()


    init {
        fetchGames()
        searchGame()
    }

    val gamesPage = Pager(PagingConfig(pageSize = 3)) {
        GamesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    private fun fetchGames(search: String? = null) {
        apiJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result =
                    if (search.isNullOrEmpty()) repository.getGames() else repository.getGamesByName(
                        search
                    )
                _games.value = result
            }
        }
    }

    private fun searchGame() {
        viewModelScope.launch(Dispatchers.IO) {
            searchFlow
                .debounce(500)
                .filter { it.isNotEmpty() } // Filter out empty input to avoid unnecessary requests
                .collect { latestValue ->
                    println("Searching for: $latestValue")
                    apiJob?.cancel()
                    fetchGames(latestValue)
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

    fun setSearchValue(value: String) {
        _searchFlow.value = value
    }

    fun clean() {
        state = GameState()
        apiJob?.cancel()
    }
}