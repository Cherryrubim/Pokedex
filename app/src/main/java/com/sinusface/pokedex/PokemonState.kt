package com.sinusface.pokedex

import com.sinusface.pokedex.data.remote.model.Pokemon

data class PokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)