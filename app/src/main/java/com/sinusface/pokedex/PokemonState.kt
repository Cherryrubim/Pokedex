package com.sinusface.pokedex

import com.sinusface.pokedex.data.remote.model.Pokemon

data class PokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingPager: Boolean = false,
    val error: String = "",
)