package com.cherryrubim.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Species(
    val name: String,
    val url: String
): Parcelable