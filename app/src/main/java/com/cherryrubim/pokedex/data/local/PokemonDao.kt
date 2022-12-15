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

package com.cherryrubim.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity LIMIT :pageSize OFFSET :page")
    suspend fun getPokemonList(page: Int, pageSize: Int = LIMIT_POKEMONS): List<PokemonEntity>

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearDB()
}