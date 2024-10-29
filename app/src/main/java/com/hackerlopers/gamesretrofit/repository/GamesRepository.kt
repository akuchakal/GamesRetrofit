package com.hackerlopers.gamesretrofit.repository

import com.hackerlopers.gamesretrofit.data.ApiGames
import com.hackerlopers.gamesretrofit.model.GameList
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getGames(): List<GameList> {
        val response = apiGames.getGames()
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        }
        else {
            return emptyList()
        }
    }

}