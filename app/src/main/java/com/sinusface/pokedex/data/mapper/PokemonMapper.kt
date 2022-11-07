package com.sinusface.pokedex.data.mapper

import com.sinusface.pokedex.data.local.entity.PokemonEntity
import com.sinusface.pokedex.data.remote.model.Pokemon

fun PokemonEntity.toPokemon(): Pokemon{
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