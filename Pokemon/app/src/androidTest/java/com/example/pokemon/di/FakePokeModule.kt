package com.example.pokemon.di

import com.example.pokemon.api.IPokeRepository
import com.example.pokemon.di.FakePokeRepository
import com.example.pokemon.di.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [RepositoryModule::class]
//)
//abstract class FakePokeModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindFakePokeRepository(pokeRepository: FakePokeRepository):IPokeRepository
//}
