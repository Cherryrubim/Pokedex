package com.cherryrubim.pokedex.domain.model.idk

data class SpeciesInfo(
    val base_happiness: Int,
    val capture_rate: Int,
    val flavor_text_entries: List<FlavorTextEntry>,
    val form_descriptions: List<FormDescription>,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
)