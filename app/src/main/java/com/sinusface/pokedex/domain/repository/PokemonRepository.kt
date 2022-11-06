package com.sinusface.pokedex.domain.repository

import com.sinusface.pokedex.domain.model.Pokemon
import com.sinusface.pokedex.domain.model.PokemonResponseBody
import com.sinusface.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(offset: Int): Flow<Resource<PokemonResponseBody>>

    fun getPokemon(name: String): Flow<Resource<Pokemon>>

}