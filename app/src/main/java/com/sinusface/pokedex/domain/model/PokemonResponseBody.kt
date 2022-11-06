package com.sinusface.pokedex.domain.model

import com.squareup.moshi.Json

data class PokemonResponseBody(
    val count: Int,
    val next: String,
    val previous: Any,
   @field:Json(name = "results") val pokemonList: List<Result>
)