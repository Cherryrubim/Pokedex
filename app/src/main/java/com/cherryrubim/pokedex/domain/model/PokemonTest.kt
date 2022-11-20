package com.cherryrubim.pokedex.domain.model

data class PokemonTest(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Species,
    val stats: List<StatXX>,
    val types: List<TypeXX>,
)