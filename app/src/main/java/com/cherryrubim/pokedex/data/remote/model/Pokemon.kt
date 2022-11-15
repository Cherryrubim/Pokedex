package com.cherryrubim.pokedex.data.remote.model

import com.squareup.moshi.Json

/*@JsonClass(generateAdapter = true)*/
data class Pokemon(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)