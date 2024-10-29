package com.hackerlopers.gamesretrofit.model

import com.google.gson.annotations.SerializedName

data class GamesModel(
    val count: Int,
    val results: List<GameList>
)

data class GameList(
    val id: Int,
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String
)
