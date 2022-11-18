package com.cherryrubim.pokedex

import com.cherryrubim.pokedex.domain.model.Pokemon

data class PokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoadingInEmptyList: Boolean = false, // <- Loading for EmptyList
    val isLoadingNextPage: Boolean = false, // <- Loading for NextPage
    val isErrorInEmptyList: Boolean = false, // <- Error for EmptyList
    val isErrorPageNextRequest: Boolean = false, // <- Error for NextResquest
)