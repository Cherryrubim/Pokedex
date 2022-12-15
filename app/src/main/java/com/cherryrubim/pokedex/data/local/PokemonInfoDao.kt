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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.data.local.entity.PokemonInfoEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE name =:name")
    suspend fun getPokemonInfo(name: String): PokemonInfoEntity?

    @Query("DELETE FROM PokemonInfoEntity")
    suspend fun clearDB()
}