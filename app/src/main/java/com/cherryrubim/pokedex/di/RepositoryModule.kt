package com.cherryrubim.pokedex.di

import com.cherryrubim.pokedex.data.PokemonRepositoryImpl
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
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
    abstract fun bindRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}