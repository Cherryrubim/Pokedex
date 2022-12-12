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
