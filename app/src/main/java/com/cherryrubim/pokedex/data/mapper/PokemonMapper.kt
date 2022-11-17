package com.cherryrubim.pokedex.data.mapper

import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.domain.model.Pokemon

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