package com.example.pokemon.api

import com.example.pokemon.model.Pokemon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"

interface PokeApiService {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id:String) : Pokemon
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object PokeApi{
    val retrofitService : PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}