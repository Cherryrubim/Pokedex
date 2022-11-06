package com.sinusface.pokedex.di

import com.sinusface.pokedex.data.PokemonRepositoryImpl
import com.sinusface.pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}