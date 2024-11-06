package com.hackerlopers.gamesretrofit.data

import com.hackerlopers.gamesretrofit.model.GamesModel
import com.hackerlopers.gamesretrofit.model.SingleGameModel
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.API_KEY
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.ENDPOINT
import com.hackerlopers.gamesretrofit.utils.Constants.Companion.SINGLE_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGames {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id") id: Int): Response<SingleGameModel>

    @GET(ENDPOINT)
    suspend fun getGamesByName(
        @Query("search") search: String,
        @Query("key") key: String = SINGLE_API_KEY
    ): Response<GamesModel>

    @GET(ENDPOINT)
    suspend fun getGamesPaging(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") key: String = SINGLE_API_KEY
    ): GamesModel
}