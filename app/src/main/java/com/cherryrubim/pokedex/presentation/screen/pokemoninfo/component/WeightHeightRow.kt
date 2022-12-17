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

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.ui.theme.PokedexTheme

@Composable
fun WeightHeightRow(
    modifier: Modifier = Modifier,
    state: PokemonDetailState = PokemonDetailState(),
    colorBackground: Color = Color.White,
    tintIcon: Color = SnolaxColor,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(15.dp),
    horizontalPadding: Dp = 12.dp,
    iconSpace: Dp = 4.dp
) {

    val TAG = "WeightHeightRow"

    val weight = remember {
        Log.i(TAG, "Weight")
        mutableStateOf("-.-")
    }

    val height = remember {
        Log.i(TAG, "Height")
        mutableStateOf("-.-")
    }

    LaunchedEffect(state.pokemonInfo) {
        state.pokemonInfo?.let {
            Log.i(TAG, "Height: ")
            weight.value = (it.weight.toFloat() / 10).toString()
            height.value = (it.height.toFloat() / 10).toString()
        }
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .widthIn(130.dp)
                .clip(roundedCornerShape)
                .background(colorBackground)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontalPadding, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(iconSpace)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.weight_24),
                        contentDescription = "weight",
                        tint = tintIcon
                    )
                    Text(
                        text = "${weight.value} Kg",
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = tintIcon,
                        style = TextStyle(
                            lineHeight = 2.5.em,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false,
                            )
                        )
                    )
                }
                Text(
                    text = "Weight",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal,
                    color = tintIcon,
                    style = TextStyle(
                        lineHeight = 2.5.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        )
                    )
                )
            }
        }

        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 10.dp),
            color = colorBackground
        )

        Box(
            modifier = Modifier
                .widthIn(130.dp)
                .clip(roundedCornerShape)
                .background(colorBackground)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontalPadding, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(iconSpace)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_straighten_24_),
                        contentDescription = "straighten",
                        tint = tintIcon
                    )
                    Text(
                        text = "${height.value} m",
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = tintIcon,
                        style = TextStyle(
                            lineHeight = 2.5.em,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false,
                            )
                        )
                    )
                }
                Text(
                    text = "Height",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal,
                    color = tintIcon,
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
}

@Composable
@Preview
fun WeightHeightRowPreview(){
    PokedexTheme() {
        WeightHeightRow(state = PokemonDetailState(PokemonInfo(weight = 100, height = 19)))
    }
}