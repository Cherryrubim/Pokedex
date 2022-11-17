package com.cherryrubim.pokedex.domain.model

import com.squareup.moshi.Json

/*@JsonClass(generateAdapter = true)*/
data class Pokemon(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
){
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}