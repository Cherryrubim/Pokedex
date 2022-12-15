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

package com.cherryrubim.pokedex.data.mapper

import com.cherryrubim.pokedex.domain.model.*
import com.cherryrubim.pokedex.domain.model.PokemonType.*

fun PokemonInfo.TypeXX.toPokemonType(): PokemonType? {
    when(this.type.name){
        "bug" -> {return BUG }
        "dark" -> {return DARK}
        "dragon" -> {return DRAGON}
        "electric" -> {return ELECTRIC}
        "fairy" -> {return FAIRY}
        "fighting" -> {return FIGHTING}
        "fire" -> {return FIRE}
        "flying" -> {return FLYING}
        "ghost" -> {return GHOST}
        "grass" -> {return GRASS}
        "ground" -> {return GROUND}
        "ice" -> {return ICE}
        "normal" -> {return NORMAL}
        "poison" -> {return POISON}
        "psychic" -> {return PSYCHIC}
        "rock" -> {return ROCK}
        "steel" -> {return STEEL}
        "water" -> {return WATER}
        else -> {return null}
    }
}

fun PokemonInfo.StatXX.toStat(): Stat {

    return when(this.stat.name){

        "special-attack" -> {
            return Stat("Sp. Atk", this.base_stat)
        }

        "special-defense" -> {
            return Stat("Sp. Def", this.base_stat)
        }

        else -> {
            return Stat(stat.name.capitalize(), this.base_stat)
        }
    }
}
