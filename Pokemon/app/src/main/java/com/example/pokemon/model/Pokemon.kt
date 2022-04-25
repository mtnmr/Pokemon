package com.example.pokemon.model

import com.squareup.moshi.Json

data class Pokemon (
    val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
    val sprites : PokeSprite
)

data class  PokeSprite(
    @Json(name = "front_default") val frontDefault : String,
    @Json(name = "back_default") val backDefault : String
)