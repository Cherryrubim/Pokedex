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

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cherryrubim.pokedex.data.mapper.toStat
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.domain.model.PokemonInfo.StatXX
import com.cherryrubim.pokedex.domain.model.PokemonInfo.StatXXX
import com.cherryrubim.pokedex.domain.model.Stat
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState
import com.cherryrubim.pokedex.ui.theme.*

@Composable
fun StatsBox(
    modifier: Modifier = Modifier,
    state: PokemonDetailState,
    textColor: Color = Color.Black,
    background: Color = Color.White,
    dominantColor: Color = SnolaxColor,
    highlightColor: Color = SnolaxColor,
    backgroundBarColor: Color = GrayLight,
    delayPerItem: Int = 500,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(background)
            .animateContentSize()
    ){

        if (state.isLoading){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp)
                )
            }
        }

        state.pokemonInfo?.stats?.let {

            Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                val topStat = it.maxOfOrNull { it.base_stat.toFloat() } ?: 0F

                it.forEachIndexed { index, item ->

                    var statBarcolor = dominantColor
                    var _textColor = textColor

                    if (item.base_stat >= topStat) {
                        statBarcolor = highlightColor
                        _textColor = highlightColor
                    }

                    BarStat(
                        stat = item.toStat(),
                        statBarColor = statBarcolor,
                        //statNameColor = _textColor,
                        statValueColor = _textColor,
                        maxStat = topStat,
                        backgroundBarColor = backgroundBarColor,
                        delayMillis = index * delayPerItem
                    )
                }
            }


        }
    }
}

@Composable
fun BarStat(
    stat: Stat = Stat("Defense", 50),
    maxStat: Float = 255F,
    durationMillis: Int = 500,
    delayMillis: Int = 0,
    statNameColor: Color = GrayNormal,
    statValueColor: Color = Color.Black,
    backgroundBarColor: Color = GrayLight,
    statBarColor: Color = SnolaxColor,
) {

    val statValue = remember {
        Log.i("BarStat", "Stat: ${stat.baseStat}")
        if (stat.baseStat > maxStat) {
            return@remember 1F
        } else {
            Log.i("BarStat", "Stat Calculation Result: ${ stat.baseStat.toFloat() / maxStat}")
            return@remember stat.baseStat.toFloat() / maxStat
        }
    }

    val isAnimated = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        isAnimated.value = true
    }

    val widthBarPercentage = animateFloatAsState(
        targetValue = if (isAnimated.value) statValue else 0F,
        animationSpec = tween(
            durationMillis = durationMillis,
            delayMillis = delayMillis
        )
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            modifier = Modifier.widthIn(62.dp),
            text = stat.name,
            fontFamily = Raleway,
            fontWeight = FontWeight.Medium,
            color = statNameColor,
            style = TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                )
            )
        )

        Text(
            text = stat.baseStat.toString(),
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            color = statValueColor,
            style = TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                )
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(CircleShape)
                .background(backgroundBarColor)
                .animateContentSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(widthBarPercentage.value)
                    .height(12.dp)
                    .clip(CircleShape)
                    .background(statBarColor)
            )
        }
    }
}

@Preview
@Composable
fun PreviewStatBar(){
    BarStat()
}

@Preview
@Composable
fun PreviewStatsBox(){
    val stats = listOf<StatXX>(
        StatXX(45, 0, StatXXX("hp")),
        StatXX(49, 0, StatXXX("attack")),
        StatXX(49, 0, StatXXX("defense")),
        StatXX(65, 0, StatXXX("special-attack")),
        StatXX(65, 0, StatXXX("special-defense")),
        StatXX(45, 0, StatXXX("speed"))
    )
    val pokemonInfo = PokemonInfo(stats = stats)
    val state = PokemonDetailState(pokemonInfo = pokemonInfo, isLoading = false)
    StatsBox(state = state)
}
