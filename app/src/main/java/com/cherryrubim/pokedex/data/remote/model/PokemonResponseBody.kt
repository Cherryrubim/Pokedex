package com.cherryrubim.pokedex.data.remote.model

import com.cherryrubim.pokedex.domain.model.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseBody(
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val pokemonList: List<Pokemon>
)