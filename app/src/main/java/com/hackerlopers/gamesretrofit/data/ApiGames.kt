package com.hackerlopers.gamesretrofit.data

import com.hackerlopers.gamesretrofit.model.GamesModel
import com.hackerlopers.gamesretrofit.model.SingleGameModel
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.API_KEY
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGames {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id")id: Int): Response<SingleGameModel>
}