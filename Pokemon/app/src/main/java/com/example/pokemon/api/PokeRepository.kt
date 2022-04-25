package com.example.pokemon.api

import com.example.pokemon.model.Pokemon

class PokeRepository {

    suspend fun getPoke(id : String) : Pokemon =
        PokeApi.retrofitService.getPokemon(id)


    companion object{
        @Volatile
        private var INSTANCE:PokeRepository ?= null

        fun getRepository():PokeRepository{
            return INSTANCE ?: synchronized(this){
                val instance = PokeRepository()
                INSTANCE = instance

                instance
            }
        }
    }
}