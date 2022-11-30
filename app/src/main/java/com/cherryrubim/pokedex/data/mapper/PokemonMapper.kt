package com.cherryrubim.pokedex.data.mapper

import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.PokemonType
import com.cherryrubim.pokedex.domain.model.PokemonType.*
import com.cherryrubim.pokedex.domain.model.TypeXX

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        url = url
    )
}

fun Pokemon.toPokemonEntity(): PokemonEntity{
    return PokemonEntity(
        name = name,
        url = url
    )
}

fun TypeXX.toPokemonType(): PokemonType? {
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
