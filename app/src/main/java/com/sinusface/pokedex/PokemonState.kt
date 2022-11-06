package com.sinusface.pokedex

import com.sinusface.pokedex.domain.model.Result

data class PokemonState(
    val pokemonList: List<Result> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)