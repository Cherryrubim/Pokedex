package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.*
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component.*
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxBackgroundColor
import com.cherryrubim.pokedex.util.generateOnColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Destination
@Composable
fun PokemonDetail(
    index: Int = 0,
    pokemon: Pokemon,
    color: Int = 0,
    navigator: DestinationsNavigator,
    viewModel: PokemonInfoViewModel = hiltViewModel()
) {
    val TAG = "PokemonInfo"

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val colorSchemeBackground = colorScheme.background

    //Only Debug use
/*    val speciesInfo = SpeciesInfo(flavor_text_entries = listOf(
        FlavorTextEntry(
            flavor_text = stringResource(id = R.string.lorem_ipsum),
            language = Language("en"),
            version =  Version("Diamond")
        )))

    val state = PokemonInfoState(pokemonDescription = speciesInfo)*/
    val state = viewModel.state

    val horizontalPadding: Dp = 20.dp
    val topBarPaddingValues = PaddingValues(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 8.dp)
    val modifier: Modifier = Modifier.padding(horizontal = horizontalPadding)

    val colorText = remember {
        mutableStateOf(Color.Black)
    }

    val backgroundColor = remember {
        mutableStateOf(SnolaxBackgroundColor)
    }

    val flavorTextEntry = remember(state.pokemonDescription) {
        if (state.pokemonDescription != null) {
            with(state.pokemonDescription) {
                val pokemonDescription =
                    flavor_text_entries.find { flavorTextEntry -> flavorTextEntry.language.name == "es" && flavorTextEntry.version.name == "sword" }
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

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        // setStatusBarColor() and setNavigationBarColor() also exist
        onDispose {
            systemUiController.setSystemBarsColor(
                color = colorSchemeBackground,
                darkIcons = useDarkIcons
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { drawRect(color = backgroundColor.value) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {

            TopBarCustom(
                color = colorText.value,
                onClick = { navigator.popBackStack() }
            )

            Spacer(modifier = Modifier.size(8.dp))

            IndexAndPokemonTypeRow(
                modifier = modifier,
                index = index,
                state = state,
                colorIndex = colorText.value
            )
            PokemonName(
                modifier = modifier,
                pokemon = pokemon,
                colorText = colorText.value
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(82.dp, 5.dp) //Must probe widthIn()
                    .aspectRatio(1F / 1F)
            ) {

                CoilImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageModel = { pokemon.getImageUrl() },
                    imageOptions = ImageOptions(
                        alignment = Alignment.Center,
                        contentDescription = pokemon.name
                    ),
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
                                palette.dominantSwatch?.rgb?.let { dominantColor ->
                                    //The solution must be Testing. Pending!!
                                    if (Color(dominantColor).luminance() < 0.1) {
                                        palette.lightVibrantSwatch?.rgb?.let { vibrantColor ->
                                            // ->Do MutableState value Here <-//
                                            backgroundColor.value = Color(vibrantColor)
                                            colorText.value =
                                                Color(vibrantColor).generateOnColor()
                                        }
                                    } else {
                                        Log.i(TAG, "Palette: DominantColor")
                                        // ->Do MutableState value Here <-//
                                        backgroundColor.value = Color(dominantColor)
                                        colorText.value = Color(dominantColor).generateOnColor()
                                    }
                                }
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            PokemonDescriptor(
                modifier = modifier,
                state = state,
                flavorTextEntry = flavorTextEntry
            )

            Spacer(modifier = Modifier.size(20.dp))

            WeightHeightRow(
                modifier = modifier,
                state = state
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                modifier = modifier,
                text = "Stats",
                fontFamily = Raleway,
                fontWeight = FontWeight.Medium,
                color = colorText.value,
                fontSize = 20.sp,
                style = TextStyle(
                    lineHeight = 2.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false,
                    )
                )
            )

            Spacer(modifier = Modifier.size(14.dp))

            StatsBox(
                modifier = modifier,
                state = state,
                //textColor = colorText.value,
                dominantColor = backgroundColor.value,
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}


