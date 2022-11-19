package com.example.pokemon.di

import com.example.pokemon.api.IPokeRepository
import com.example.pokemon.api.PokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsPokeRepository(repository: PokeRepository):IPokeRepository
}