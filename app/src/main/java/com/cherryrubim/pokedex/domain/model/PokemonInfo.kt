package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import com.cherryrubim.pokedex.domain.model.PokemonType.*
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PokemonInfo(
    val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<StatXX> = emptyList(),
    val types: List<TypeXX> = emptyList(),
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Species(
        val name: String,
        val url: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class StatXX(
        val base_stat: Int,
        val effort: Int,
        val stat: StatXXX
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class StatXXX(
        val name: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class TypeXX(
        val slot: Int,
        val type: TypeXXX
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class TypeXXX(
        val name: String,
        val url: String
    ) : Parcelable
}