package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonInfo(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Species,
    val stats: List<StatXX>,
    val types: List<TypeXX>,
): Parcelable