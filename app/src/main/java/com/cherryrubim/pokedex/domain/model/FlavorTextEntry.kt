package com.cherryrubim.pokedex.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version: Version
)