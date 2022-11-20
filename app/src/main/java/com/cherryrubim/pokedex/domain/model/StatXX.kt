package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatXX(
    val base_stat: Int,
    val effort: Int,
    val stat: StatXXX
): Parcelable