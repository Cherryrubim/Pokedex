package com.cherryrubim.pokedex

import com.cherryrubim.pokedex.data.remote.model.Pokemon

data class PokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingPager: Boolean = false,
    val error: String = "",
)