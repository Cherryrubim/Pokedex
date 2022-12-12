package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Pokemon(
    val page: Int = 0,
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "url") val url: String = ""
): Parcelable{
    fun getImageUrl(): String {
        if(url.isNotBlank()){
            val index = url.split("/".toRegex()).dropLast(1).last() ?: ""
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
        }else{
            return ""
        }
    }
}