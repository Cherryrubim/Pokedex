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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonDetailState
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.cherryrubim.pokedex.domain.model.FlavorTextEntry
import com.cherryrubim.pokedex.util.removeDuplicateSpace

@Composable
fun PokemonDescriptor(
    modifier: Modifier = Modifier,
    state: PokemonDetailState = PokemonDetailState(),
    flavorTextEntry: FlavorTextEntry? = null,
    cornerSize: Dp = 10.dp,
    horizontalPaddingText: Dp = 15.dp,
    topPaddingText: Dp = 18.dp,
    bottomPaddingText: Dp = 12.dp,
    backgroundColorTitle: Color = Color.Black,
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerSize))
                    .align(Alignment.Center)
                    .background(backgroundColor)
            ) {

                Text(
                    modifier = Modifier.padding(
                        start = horizontalPaddingText,
                        end = horizontalPaddingText,
                        top = topPaddingText,
                        bottom = bottomPaddingText
                    ),
                    text = it.flavor_text.removeDuplicateSpace(),
                    fontFamily = Raleway,
                    color = textColor,
                    maxLines = 8
                )
            }

            Box(
                modifier = Modifier
                    .offset(x = 10.dp, y = -12.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(backgroundColorTitle)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    text = "Pokédex ${it.version.name.capitalize()}",
                    color = SnolaxColor,
                    fontFamily = Raleway,
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