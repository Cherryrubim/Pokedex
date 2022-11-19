package com.cherryrubim.pokedex.presentation.navigation

sealed class Screen(val route: String) {
    object PokemonList: Screen("pokemonList")
    object PokemonInfo: Screen("pokemonInfo")
}