package com.cherryrubim.pokedex.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.ui.theme.*

enum class PokemonType(
    val type: String,
    @DrawableRes val id: Int,
    val color: Color
) {
    BUG(type = "Bug", id = R.drawable.bug, color = BugColor),
    DARK(type = "Dark", id = R.drawable.dark, color = DarkColor),
    DRAGON(type = "Dragon", id = R.drawable.dragon, color = DragonColor),
    ELECTRIC(type= "Electric", id = R.drawable.electric, color = ElectricColor),
    FAIRY(type = "Fairy", id = R.drawable.fairy, color = FairyColor),
    FIGHTING(type = "Fighting", id = R.drawable.fighting, color = FightingColor),
    FIRE(type = "Fire", id = R.drawable.fire, color = FireColor),
    FLYING(type = "Flying", id = R.drawable.flying, color = FlyingColor),
    GHOST(type = "Ghost", id = R.drawable.ghost, color = GhostColor),
    GRASS(type = "Grass", id = R.drawable.grass, color = GrassColor),
    GROUND(type = "Ground", id = R.drawable.ground, color = GroundColor),
    ICE(type = "Ice", id = R.drawable.ice, color = IceColor),
    POISON(type = "Poison", id = R.drawable.poison, color = PoisonColor),
    NORMAL(type = "Normal", id = R.drawable.normal, color = NormalColor),
    PSYCHIC(type = "Psychic", id = R.drawable.psychic, color = PsychicColor),
    ROCK(type = "Rock", id = R.drawable.rock, color = RockColor),
    STEEL(type = "Steel", id = R.drawable.steel, color = SteelColor),
    WATER(type = "Water", id = R.drawable.water, color = WaterColor);

    //Mapping Type From API to PokemonType
    companion object {
        fun getType(type: String?): PokemonType?{
            when(type){
                "bug" -> {return BUG}
                "dark" -> {return DARK}
                "dragon" -> {return DRAGON}
                "electric" -> {return ELECTRIC}
                "fairy" -> {return FAIRY}
                "fighting" -> {return FIGHTING}
                "fire" -> {return FIRE}
                "flying" -> {return FLYING}
                "ghost" -> {return GHOST}
                "grass" -> {return GRASS}
                "ground" -> {return GROUND}
                "ice" -> {return ICE}
                "normal" -> {return NORMAL}
                "poison" -> {return POISON}
                "psychic" -> {return PSYCHIC}
                "rock" -> {return ROCK}
                "steel" -> {return STEEL}
                "water" -> {return WATER}
                else -> {return null}
            }
        }
    }
}