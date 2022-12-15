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