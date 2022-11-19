package com.example.pokemon.api

import com.example.pokemon.model.Pokemon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokeRepository @Inject constructor():IPokeRepository {

    @Inject
    lateinit var pokeApiService: PokeApiService


    override suspend fun getPoke(id : String) : Pokemon =
        pokeApiService.getPokemon(id)


//    companion object{
//        @Volatile
//        private var INSTANCE:PokeRepository ?= null
//
//        fun getRepository():PokeRepository{
//            return INSTANCE ?: synchronized(this){
//                val instance = PokeRepository()
//                INSTANCE = instance
//
//                instance
//            }
//        }
//    }
}