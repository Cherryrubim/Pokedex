package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import com.cherryrubim.pokedex.domain.model.PokemonType.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonInfo(
    val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val species: Species? = null,
    val stats: List<StatXX> = emptyList(),
    val types: List<TypeXX> = emptyList(),
) : Parcelable

@Parcelize
data class Species(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class StatXX(
    val base_stat: Int,
    val effort: Int,
    val stat: StatXXX
) : Parcelable

@Parcelize
data class StatXXX(
    val name: String
) : Parcelable

@Parcelize
data class TypeXX(
    val slot: Int,
    val type: TypeXXX
) : Parcelable

@Parcelize
data class TypeXXX(
    val name: String,
    val url: String
) : Parcelable