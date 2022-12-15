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

package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import com.cherryrubim.pokedex.domain.model.PokemonType.*
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PokemonInfo(
    val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<StatXX> = emptyList(),
    val types: List<TypeXX> = emptyList(),
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Species(
        val name: String,
        val url: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class StatXX(
        val base_stat: Int,
        val effort: Int,
        val stat: StatXXX
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class StatXXX(
        val name: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class TypeXX(
        val slot: Int,
        val type: TypeXXX
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class TypeXXX(
        val name: String,
        val url: String
    ) : Parcelable
}