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

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cherryrubim.pokedex.data.local.converters.FlavorTextEntryConverter
import com.cherryrubim.pokedex.data.local.converters.StatsConverter
import com.cherryrubim.pokedex.data.local.converters.TypeConverter
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.data.local.entity.PokemonInfoEntity
import com.cherryrubim.pokedex.data.local.entity.PokemonSpeciesEntity
import com.cherryrubim.pokedex.domain.model.PokemonSpecies

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class, PokemonSpeciesEntity::class],
    version = 3,
    exportSchema = true // <- ONLY DEBUGE USED
)
@TypeConverters(value = [TypeConverter::class, StatsConverter::class, FlavorTextEntryConverter::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
    abstract fun pokemonSpeciesDao(): PokemonSpeciesDao
}