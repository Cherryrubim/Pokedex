package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.cherryrubim.pokedex.util.generateOnColor
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Destination
@Composable
fun PokemonInfo(
    pokemon: Pokemon,
    color: Int = 0,
    viewModel: PokemonInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val horizontalPadding: Dp = 20.dp
    val topBarPaddingValues = PaddingValues(horizontal = 10.dp, vertical = 15.dp)
    val modifier: Modifier = Modifier.padding(horizontal = horizontalPadding)

    val colorText = remember {
        mutableStateOf(Color.Black)
    }

    val flavorTextEntry = remember(state.pokemonDescription) {
        if (state.pokemonDescription != null) {
            with(state.pokemonDescription) {
                val pokemonDescription =
                    flavor_text_entries.find { flavorTextEntry -> flavorTextEntry.language.name == "en" }

                if (pokemonDescription != null) {
                    return@remember pokemonDescription
                } else {
                    return@remember null
                }
            }
        } else {
            return@remember null
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF93C9AD))
            .scrollable(orientation = Orientation.Vertical, state = rememberScrollState())
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(orientation = Orientation.Vertical, state = rememberScrollState())
        ) {

           // Spacer(modifier = Modifier.size(12.dp))

            TopBar(modifier = Modifier.padding(paddingValues = topBarPaddingValues))

           // Spacer(modifier = Modifier.size(12.dp))

            state.pokemonInfo?.let { pokemonInfo ->
                with(pokemonInfo) {
                    IndexAndPokemonTypeRow(
                        modifier = modifier,
                        index = id,
                        colorIndex = colorText.value
                    )
                    NameAndPokemonType2Row(
                        modifier = modifier,
                        name = name,
                        colorName = colorText.value
                    )

                    CoilImage(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .aspectRatio(1F / 1F),
                        imageModel = { pokemon.getImageUrl() },
                        previewPlaceholder = R.drawable.ivasaur,
                        loading = {
                            Box(modifier = Modifier.matchParentSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        component = rememberImageComponent {
                            +PalettePlugin(
                                imageModel = pokemon.getImageUrl(),
                                useCache = true,
                                paletteLoadedListener = { palette ->
                                    palette.dominantSwatch?.rgb?.let { colorValue ->
                                        //The solution must be Test. (????
                                        if (Color(colorValue).luminance() < 0.1) {
                                            palette.vibrantSwatch?.rgb?.let { vibrantColor ->
                                                // ->Do MutableState value Here <-//
                                                colorText.value =
                                                    Color(vibrantColor).generateOnColor()
                                            }
                                        } else {
                                            // ->Do MutableState value Here <-//
                                            colorText.value = Color(colorValue).generateOnColor()
                                        }
                                    }
                                }
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    flavorTextEntry?.let { flavorTextEntry ->
                        with(flavorTextEntry) {
                            PokemonDescriptor(
                                modifier = modifier,
                                titleVersion = version.name,
                                text = flavor_text
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(20.dp))

                    WeightHeightRow(
                        modifier = modifier,
                        weight = weight.div(10).toString(),
                        height = height.div(10).toString()
                    )
                }
            }
        }
    }

/*    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        *//*Log.i("PokemonInfo", "State Loading: ${state.isLoading}")*//*
        Log.i("PokemonInfo", "State: ${state}")
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            state.pokemonInfo?.let { pokemonInfo ->
                with(pokemonInfo) {
                    Text(text = id.toString(), fontSize = 40.sp)
                    Text(text = name, fontSize = 40.sp)
                    Text(text = weight.toString(), fontSize = 40.sp)
                    Text(text = height.toString(), fontSize = 40.sp)
                }
            }

            state.pokemonDescription?.let { speciesInfo ->
                with(speciesInfo) {
                    val pokemonDescription = flavor_text_entries.find { flavorTextEntry ->
                        flavorTextEntry.language.name == "en"
                    }

                    pokemonDescription?.let { flavorTextEntry ->
                        val description = flavorTextEntry.flavor_text
                        Text(text = description, fontSize = 16.sp, color = SnolaxColor)
                    }

                }
            }

        }
    }*/






}

@Preview(showSystemUi = true, device = Devices.NEXUS_5)
@Composable
fun test() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF93C9AD))
    ) {

        val horizontalPadding: Dp = 20.dp
        val topBarPaddingValues = PaddingValues(horizontal = 10.dp, vertical = 20.dp)
        val modifier: Modifier = Modifier.padding(horizontal = horizontalPadding)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.size(12.dp))
            TopBar(modifier = Modifier.padding(paddingValues = topBarPaddingValues))

            Spacer(modifier = Modifier.size(12.dp))

            IndexAndPokemonTypeRow(modifier)
            NameAndPokemonType2Row(modifier)

            CoilImage(
                modifier = Modifier
                    .padding(horizontal = 80.dp, vertical = 8.dp)
                    .aspectRatio(1F / 1F),
                imageModel = { R.drawable.ivasaur },
                previewPlaceholder = R.drawable.ivasaur
            )

            Spacer(modifier = Modifier.size(8.dp))

            PokemonDescriptor(modifier)

            Spacer(modifier = Modifier.size(20.dp))

            WeightHeightRow(modifier)
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, color: Color = Color.White) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier.clickable { 
            
        }) {
            Icon(
                painterResource(
                    id = R.drawable.ic_arrow_back_32
                ),
                contentDescription = "back",
                modifier = Modifier.size(32.dp),
                tint = color
            )
        }

/*        IconButton(
            onClick = { *//*TODO*//* }
        ) {
            Icon(
                painterResource(
                    id = R.drawable.ic_arrow_back_32
                ),
                contentDescription = "back",
                modifier = Modifier.size(48.dp),
                tint = color
            )
        }*/

        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painterResource(
                    id = R.drawable.ic_favorite_fill_32
                ),
                contentDescription = "favorite",
                modifier = Modifier.size(32.dp),
                tint = color
            )
        }
    }
}

@Composable
fun IndexAndPokemonTypeRow(
    modifier: Modifier = Modifier,
    index: Int = 0,
    type: String = "????",
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

        Box(
            modifier = Modifier
                .clip(roundedCornerShape)
                .background(Color.White)
                .border(
                    BorderStroke(1.dp, SnolaxColor),
                    roundedCornerShape
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Type 1"
                )

                Text(
                    text = type,
                    fontWeight = FontWeight.Light,
                    color = SnolaxColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun NameAndPokemonType2Row(
    modifier: Modifier = Modifier,
    name: String = "Ivysaur",
    type2: String = "????",
    colorName: Color = Color.White,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(20.dp)
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = colorName,
            style = TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                )
            )
        )

        Box(
            modifier = Modifier
                .clip(roundedCornerShape)
                .background(Color.White)
                .border(BorderStroke(1.dp, SnolaxColor), roundedCornerShape)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Type 1"
                )

                Text(
                    text = type2,
                    fontWeight = FontWeight.Light,
                    color = SnolaxColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun PokemonDescriptor(
    modifier: Modifier = Modifier,
    titleVersion: String = "Diamond",
    text: String = stringResource(id = R.string.lorem_ipsum),
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
                text = text,
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
                text = "Pokédex $titleVersion",
                color = SnolaxColor,
                fontFamily = Raleway,
            )
        }
    }
}

@Composable
fun WeightHeightRow(
    modifier: Modifier = Modifier,
    weight: String = "0.0",
    height: String = "0.0",
    colorBackground: Color = Color.White,
    tintIcon: Color = SnolaxColor,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(15.dp),
    horizontalPadding: Dp = 24.dp
) {

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .clip(roundedCornerShape)
                .background(colorBackground)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontalPadding, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.weight_24),
                        contentDescription = "weight",
                        tint = tintIcon
                    )
                    Text(
                        text = weight,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = tintIcon
                    )
                }
                Text(
                    text = "Weight",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal,
                    color = tintIcon
                )
            }
        }

        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 5.dp),
            color = colorBackground
        )

        Box(
            modifier = Modifier
                .clip(roundedCornerShape)
                .background(colorBackground)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontalPadding, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_straighten_24_),
                        contentDescription = "straighten",
                        tint = tintIcon
                    )
                    Text(
                        text = height,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = tintIcon
                    )
                }
                Text(
                    text = "Height",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal,
                    color = tintIcon
                )
            }
        }
    }
}

/*@Preview(showBackground = true, backgroundColor = 0x93C9AD, widthDp = 200)
@Composable
fun PreviewTopBar(){
    TopBar()
}*/


