package com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.ui.theme.Raleway

@Composable
fun PokemonName(
    modifier: Modifier = Modifier,
    pokemon: Pokemon = Pokemon(),
    colorText: Color = Color.White,
) {
    Text(
        modifier = modifier,
        text = pokemon.name.capitalize(),
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = colorText,
        style = TextStyle(
            lineHeight = 2.5.em,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false,
            )
        )
    )
}