package com.cherryrubim.pokedex.data.remote

import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.model.SpeciesInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = LIMIT_POKEMONS,
        @Query("offset") offtset: Int = 0
    ): PokemonResponseBody

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonInfo


    @GET("pokemon-species/{name}")
    suspend fun getPokemonDescription(
        @Path("name") name: String
    ): SpeciesInfo


}