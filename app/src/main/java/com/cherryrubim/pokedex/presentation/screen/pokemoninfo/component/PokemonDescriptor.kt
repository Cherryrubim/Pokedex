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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.domain.model.PokemonSpecies.FlavorTextEntry
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.cherryrubim.pokedex.util.removeDuplicateSpace
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.Language
import com.cherryrubim.pokedex.domain.model.Version
import com.cherryrubim.pokedex.ui.theme.LighGray
import com.cherryrubim.pokedex.ui.theme.SnolaxColorBold

@Composable
fun PokemonDescriptor(
    modifier: Modifier = Modifier,
    state: PokemonDetailState = PokemonDetailState(),
    flavorTextEntry: FlavorTextEntry? = null,
    cornerSize: Dp = 18.dp,
    horizontalPaddingText: Dp = 15.dp,
    topPaddingText: Dp = 18.dp,
    bottomPaddingText: Dp = 8.dp,
    backgroundColorTitle: Color = LighGray,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(15.dp))
                    .background(backgroundColor)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp)
                )
            }
        }

        flavorTextEntry?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerSize))
                    .align(Alignment.Center)
                    .background(backgroundColor)
            ) {

                Text(
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 6.dp),
                    text = "Pokédex ${it.version.name.capitalize()}",
                    color = SnolaxColorBold,
                    fontFamily = Raleway,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        lineHeight = 2.5.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        )
                    )
                )

                Text(
                    modifier = Modifier.padding(
                        start = horizontalPaddingText,
                        end = horizontalPaddingText,
                        bottom = bottomPaddingText
                    ),
                    text = it.flavor_text.removeDuplicateSpace(),
                    fontFamily = Raleway,
                    color = textColor,
                    maxLines = 8,
                    style = TextStyle(
                        lineHeight = 1.2.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        )
                    )
                )
            }

/*            Box(
                modifier = Modifier
                    .offset(x = 6.dp, y = (-16).dp)
                    .clip(RoundedCornerShape(cornerSize))
                    .background(backgroundColorTitle)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 14.dp, vertical = 7.dp),
                    text = "Pokédex ${it.version.name.capitalize()}",
                    color = SnolaxColor,
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        lineHeight = 2.5.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        )
                    )
                )
            }*/
        }
    }
}

@Composable
@Preview(backgroundColor = 0x93C9AD, showBackground = true, device = Devices.NEXUS_5)
fun PokemonDescriptorPreview(){
    PokemonDescriptor(flavorTextEntry = FlavorTextEntry(stringResource(id = R.string.lorem_ipsum), language = Language("es"), Version("Shield")))
}