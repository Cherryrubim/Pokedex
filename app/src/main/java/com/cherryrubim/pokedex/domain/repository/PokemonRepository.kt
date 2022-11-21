package com.cherryrubim.pokedex.domain.repository

import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.model.Species
import com.cherryrubim.pokedex.domain.model.idk.SpeciesInfo
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(offset: Int): Flow<Resource<PokemonResponseBody>>

    fun getPokemon(name: String): Flow<Resource<PokemonInfo>>

    fun getPokemonDescription(name: String): Flow<Resource<SpeciesInfo>>

}