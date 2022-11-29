package com.cherryrubim.pokedex.domain.model

data class SpeciesInfo(
    val base_happiness: Int = 0,
    val capture_rate: Int = 0,
    val flavor_text_entries: List<FlavorTextEntry> = emptyList(),
    val form_descriptions: List<FormDescription> = emptyList(),
    val is_baby: Boolean = false,
    val is_legendary: Boolean = false,
    val is_mythical: Boolean = false,
)