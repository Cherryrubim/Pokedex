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

package com.cherryrubim.pokedex.domain.repository

import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.PokemonSpecies
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(page: Int): Flow<Resource<List<Pokemon>>>

    fun getPokemonInfo(name: String): Flow<Resource<PokemonInfo>>

    fun getPokemonDescription(name: String): Flow<Resource<PokemonSpecies>>

}