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

package com.cherryrubim.pokedex.data.local.mapper

import com.cherryrubim.pokedex.data.local.entity.PokemonInfoEntity
import com.cherryrubim.pokedex.domain.model.PokemonInfo

fun PokemonInfoEntity.toDomain(): PokemonInfo {
    return PokemonInfo(
        id = this.id,
        name = this.name,
        weight = this.weight,
        height = this.height,
        types = this.types,
        stats = this.stats
    )
}

fun PokemonInfo.toEntity(): PokemonInfoEntity{
    return PokemonInfoEntity(
        id = this.id,
        name = this.name,
        weight = this.weight,
        height = this.height,
        types = this.types,
        stats = this.stats
    )
}