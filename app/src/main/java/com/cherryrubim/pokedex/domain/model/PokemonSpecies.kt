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

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    val name: String = "",
    val flavor_text_entries: List<FlavorTextEntry> = emptyList()
    /*val form_descriptions: List<FormDescription> = emptyList()*/
){
    @JsonClass(generateAdapter = true)
    data class FlavorTextEntry(
        val flavor_text: String,
        val language: Language,
        val version: Version
    )

/*    @JsonClass(generateAdapter = true)
    data class FormDescription(
        val description: String,
        val language: Language
    )*/

}