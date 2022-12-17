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

package com.cherryrubim.pokedex.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cherryrubim.pokedex.domain.model.PokemonSpecies
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class FlavorTextEntryConverter @Inject constructor (moshi: Moshi) {

    val listType = Types.newParameterizedType(List::class.java, PokemonSpecies.FlavorTextEntry::class.java)
    val adapter = moshi.adapter<List<PokemonSpecies.FlavorTextEntry?>>(listType)

    @TypeConverter
    fun fromString(value: String): List<PokemonSpecies.FlavorTextEntry?>?{
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromObject(listStats: List<PokemonSpecies.FlavorTextEntry?>): String{
        return adapter.toJson(listStats)
    }
}