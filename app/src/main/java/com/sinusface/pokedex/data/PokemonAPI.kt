package com.sinusface.pokedex.data

import com.sinusface.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.sinusface.pokedex.domain.model.Pokemon
import com.sinusface.pokedex.domain.model.PokemonResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int = LIMIT_POKEMONS,
        @Query("offset") offtset: Int = 0
    ): PokemonResponseBody

    @GET("pokemon/{name}")
    fun getPokemon(
        @Path("name") name: String
    ): Pokemon

}