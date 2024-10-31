package com.hackerlopers.gamesretrofit.model

import com.google.gson.annotations.SerializedName

data class SingleGameModel(
    val name: String,
    @SerializedName("description_raw")
    val descriptionRaw: String,
    val metacritic: Int,
    val website: String,
    @SerializedName("background_image")
    val backgroundImage: String
)
