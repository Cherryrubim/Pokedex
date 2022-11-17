package com.cherryrubim.pokedex

import com.cherryrubim.pokedex.domain.model.Pokemon

data class PokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingPager: Boolean = false,
    val isError: Boolean = false,
)