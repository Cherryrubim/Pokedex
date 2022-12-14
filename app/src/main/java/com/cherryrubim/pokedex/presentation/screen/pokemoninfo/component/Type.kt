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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.domain.model.PokemonType
import com.cherryrubim.pokedex.domain.model.PokemonInfo.TypeXX
import com.cherryrubim.pokedex.domain.model.PokemonInfo.TypeXXX
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState

@Composable
fun Type(
    pokemonType: PokemonType,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(18.dp),
    background: Color = Color.White,
) {

    Surface(
        color = background,
        border = BorderStroke(1.dp, pokemonType.color),
        shape = roundedCornerShape,
        modifier = Modifier // <- Solution for Bug: Background is visible outside border.
            .border(
                width = 1.dp,
                color = pokemonType.color,
                shape = roundedCornerShape
            )
            .padding(1.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Icon(
                modifier = Modifier.size(22.dp),
                painter = painterResource(id = pokemonType.id),
                contentDescription = pokemonType.type,
                tint = pokemonType.color
            )

            Text(
                text = pokemonType.type,
                fontWeight = FontWeight.Medium,
                color = pokemonType.color,
                style = TextStyle(
                    lineHeight = 2.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false,
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x93C9AD, device = Devices.NEXUS_5)
@Composable
fun PreviewType() {
    val pokemonInfo = PokemonInfo(
        types = listOf(TypeXX(0, type = TypeXXX("grass", "")))
    )
    val pokemonDetailState = PokemonDetailState(pokemonInfo = pokemonInfo)
    IndexAndPokemonTypeRow(state = pokemonDetailState)
}