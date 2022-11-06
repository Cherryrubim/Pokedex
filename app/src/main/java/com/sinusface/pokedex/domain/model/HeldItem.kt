package com.sinusface.pokedex.domain.model

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)