package com.sinusface.pokedex.di

import com.sinusface.pokedex.core.AppConstants.BASE_URL
import com.sinusface.pokedex.data.PokemonAPI
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    fun providerPokemonAPI(): PokemonAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}