package com.cherryrubim.pokedex.data.local.mapper

import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.domain.model.Pokemon

fun List<PokemonEntity>.toDomain(): List<Pokemon> {
    return this.map { pokemonEntity ->
        Pokemon(
            page = pokemonEntity.page,
            name = pokemonEntity.name,
            url = pokemonEntity.url
        )
    }
}

fun List<Pokemon>.toEntity():List<PokemonEntity> {
    return this.map { pokemon ->
        PokemonEntity(
            page = pokemon.page,
            name = pokemon.name,
            url = pokemon.url
        )
    }
}