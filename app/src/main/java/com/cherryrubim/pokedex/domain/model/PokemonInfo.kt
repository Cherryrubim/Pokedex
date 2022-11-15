package com.cherryrubim.pokedex.domain.model

data class PokemonInfo(
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val location_area_encounters: String,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)