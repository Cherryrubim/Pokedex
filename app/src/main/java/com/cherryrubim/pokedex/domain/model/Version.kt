package com.cherryrubim.pokedex.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Version(
    val name: String,
)