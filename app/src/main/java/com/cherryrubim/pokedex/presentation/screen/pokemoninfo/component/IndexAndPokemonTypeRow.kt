/*
 * Copyright (c) 2022. Designed and developed by Cherryrubim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
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
import com.cherryrubim.pokedex.data.mapper.toPokemonType
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.util.secondOrNull

@Composable
fun IndexAndPokemonTypeRow(
    modifier: Modifier = Modifier,
    index: Int = 0,
    state: PokemonDetailState = PokemonDetailState(),
    colorIndex: Color = Color.White,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(20.dp)
) {

    Modifier.widthIn()

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

            val firstType = state.pokemonInfo?.types?.firstOrNull()?.toPokemonType()
            val secondType = state.pokemonInfo?.types?.secondOrNull()?.toPokemonType()

            secondType?.let { pokemonType ->
                Type(pokemonType = pokemonType)
            }

            firstType?.let { pokemonType ->
                Type(pokemonType = pokemonType)
            }
        }
    }
}