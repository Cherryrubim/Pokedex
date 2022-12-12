package com.cherryrubim.pokedex.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GrowthRate(
    val name: String,
    val url: String
)