package com.cherryrubim.pokedex.data.local.mapper

import com.cherryrubim.pokedex.data.local.entity.PokemonInfoEntity
import com.cherryrubim.pokedex.domain.model.PokemonInfo

fun PokemonInfoEntity.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = this.id,
        name = this.name,
        weight = this.weight,
        height = this.height,
        types = this.types,
        stats = this.stats
    )
}

fun PokemonInfo.toPokemonInfoEntity(): PokemonInfoEntity{
    return PokemonInfoEntity(
        id = this.id,
        name = this.name,
        weight = this.weight,
        height = this.height,
        types = this.types,
        stats = this.stats
    )
}