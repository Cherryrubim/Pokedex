package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonInfo(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
/*    val stats: List<Stat>,
    val types: List<Type>,*/
): Parcelable