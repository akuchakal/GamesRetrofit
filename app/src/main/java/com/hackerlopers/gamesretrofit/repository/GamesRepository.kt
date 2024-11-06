package com.hackerlopers.gamesretrofit.repository

import com.hackerlopers.gamesretrofit.data.ApiGames
import com.hackerlopers.gamesretrofit.model.GameList
import com.hackerlopers.gamesretrofit.model.GamesModel
import com.hackerlopers.gamesretrofit.model.SingleGameModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class GamesRepository @Inject constructor(private val apiGames: ApiGames) {

    suspend fun getGames(): List<GameList> {
        val response = apiGames.getGames()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getGameById(id: Int): SingleGameModel? {
        val response = apiGames.getGameById(id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getGamesByName(value: String): List<GameList> {
        val response = apiGames.getGamesByName(value)
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getGamesPaging(page: Int, pageSize: Int): GamesModel {
        delay(2000L)
        return apiGames.getGamesPaging(page, pageSize)
    }

}