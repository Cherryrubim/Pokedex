package com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.domain.model.PokemonType
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonInfoState
import com.cherryrubim.pokedex.ui.theme.Raleway

@Composable
fun IndexAndPokemonTypeRow(
    modifier: Modifier = Modifier,
    index: Int = 0,
    state: PokemonInfoState = PokemonInfoState(),
    colorIndex: Color = Color.White,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(20.dp)
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#${index.toString().padStart(3, '0')}",
            fontFamily = Raleway,
            fontWeight = FontWeight.Light,
            fontSize = 32.sp,
            color = colorIndex,
            style = TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                )
            )
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            val pokemonTypeFirst = state.pokemonInfo?.types?.firstOrNull()?.type?.name
            val pokemonTypeSecundary = state.pokemonInfo?.types?.getOrNull(1)?.type?.name

            Log.i("Type", state.pokemonInfo?.types.toString())

            Log.i("Type", "First Type: $pokemonTypeFirst")
            Log.i("Type", "Second Type: $pokemonTypeSecundary")

            pokemonTypeSecundary?.let { name ->
                PokemonType.getType(name)?.let { pokemonType ->
                    Type(pokemonType = pokemonType)
                }
            }

            pokemonTypeFirst?.let { name ->
               PokemonType.getType(name)?.let { pokemonType ->
                   Log.i("Type", "Call Composable First")
                   Type(pokemonType = pokemonType)
               }
            }
        }
    }
}