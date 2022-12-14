package com.cherryrubim.pokedex.domain.repository

import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.SpeciesInfo
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(page: Int): Flow<Resource<List<Pokemon>>>

    fun getPokemon(name: String): Flow<Resource<PokemonInfo>>

    fun getPokemonDescription(name: String): Flow<Resource<SpeciesInfo>>

}