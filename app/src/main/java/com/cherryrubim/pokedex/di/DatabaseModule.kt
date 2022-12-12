package com.cherryrubim.pokedex.di

import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.PokemonDao
import com.cherryrubim.pokedex.data.local.PokemonInfoDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun providerPokemonDao(appDatabase: AppDatabase): PokemonDao

    @Binds
    @Singleton
    abstract fun providerPokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao
}