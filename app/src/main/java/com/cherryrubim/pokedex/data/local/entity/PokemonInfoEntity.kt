package com.cherryrubim.pokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherryrubim.pokedex.domain.model.*

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<PokemonInfo.StatXX> = emptyList(),
    val types: List<PokemonInfo.TypeXX> = emptyList(),
)
