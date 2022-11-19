package com.example.pokemon.di

import com.example.pokemon.api.IPokeRepository
import com.example.pokemon.model.*
import javax.inject.Inject

class FakePokeRepository @Inject constructor():IPokeRepository {
    override suspend fun getPoke(id: String): Pokemon {
        return Pokemon(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            types = listOf(PokeType(1,NameApiResource("electric",""))),
            sprites = PokeSprite(
                "",
                "",
                "",
                "",
                other = SpritesOther(PokeImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"))
            )
        )
    }
}