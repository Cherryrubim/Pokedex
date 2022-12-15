/*
 * Copyright (c) 2022. Designed and developed by Cherryrubim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cherryrubim.pokedex.data.remote

import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.model.PokemonSpecies
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
    ): PokemonSpecies


}