package com.hackerlopers.gamesretrofit.data

import com.hackerlopers.gamesretrofit.model.GamesModel
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.API_KEY
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiGames {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModel>
}