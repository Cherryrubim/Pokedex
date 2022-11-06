package com.sinusface.pokedex.domain.model

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)