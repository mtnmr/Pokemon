package com.example.pokemon.api

import com.example.pokemon.model.Pokemon

interface IPokeRepository {

    suspend fun getPoke(id: String): Pokemon
}