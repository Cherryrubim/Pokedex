package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.PokemonInfo

data class PokemonInfoState(
    val pokemonInfo: PokemonInfo? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
