package com.cherryrubim.pokedex.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokedexNumber(
    val entry_number: Int,
    val pokedex: Pokedex
)