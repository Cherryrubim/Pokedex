package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
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
import com.cherryrubim.pokedex.domain.model.*
import com.cherryrubim.pokedex.domain.model.FlavorTextEntry
import com.cherryrubim.pokedex.domain.model.Language
import com.cherryrubim.pokedex.domain.model.Version
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component.IndexAndPokemonTypeRow
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component.TopBarCustom
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxBackgroundColor
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.cherryrubim.pokedex.util.generateOnColor
import com.cherryrubim.pokedex.util.removeDuplicateSpace
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Destination
@Composable
fun PokemonInfo(
    index: Int = 0,
    pokemon: Pokemon,
    color: Int = 0,
    viewModel: PokemonInfoViewModel = hiltViewModel()
) {
    val TAG = "PokemonInfo"

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val colorSchemeBackground = colorScheme.background

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

/*            systemUiController.setStatusBarColor(
                color = colorSchemeBackground,
                darkIcons = useDarkIcons
            )

            systemUiController.setNavigationBarColor(
                color = colorSchemeBackground,
                darkIcons = useDarkIcons
            )*/
        }
    }

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

    val backgroundColor = remember{
        mutableStateOf(SnolaxBackgroundColor)
    }

    val flavorTextEntry = remember(state.pokemonDescription) {
        Log.i(TAG, "Call Remember FlavorText")
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { drawRect(color = backgroundColor.value) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.size(15.dp))

            TopBarCustom(
                modifier = Modifier.padding(paddingValues = topBarPaddingValues),
                color = colorText.value
            )

            IndexAndPokemonTypeRow(
                modifier = modifier,
                index = index,
                state = state,
                colorIndex = colorText.value
            )
            NameAndPokemonType2Row(
                modifier = modifier,
                name = pokemon.name.capitalize(),
                colorName = colorText.value
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(82.dp, 5.dp)
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

/*        Box(
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
        }*/
    }
}

@Composable
fun PokemonDescriptor(
    modifier: Modifier = Modifier,
    state: PokemonInfoState = PokemonInfoState(),
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

@Composable
fun WeightHeightRow(
    modifier: Modifier = Modifier,
    state: PokemonInfoState = PokemonInfoState(),
    colorBackground: Color = Color.White,
    tintIcon: Color = SnolaxColor,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(15.dp),
    horizontalPadding: Dp = 20.dp,
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

    LaunchedEffect(state.pokemonInfo){
        state.pokemonInfo?.let {
            Log.i(TAG, "Height: ")
            weight.value = (it.weight.toFloat()/10).toString()
            height.value = (it.height.toFloat()/10).toString()
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

@Preview(showBackground = true, backgroundColor = 0x93C9AD,device = Devices.PIXEL_3)
@Composable
fun PreviewPokemonInfo() {
    PokemonInfo(
        pokemon = Pokemon("Bulbasur")
    )
}

@Preview(showBackground = true, backgroundColor = 0x93C9AD, device = Devices.NEXUS_5)
@Composable
fun PreviewPokemonDescription() {
    PokemonDescriptor(
        state = PokemonInfoState(isLoading = false),
        flavorTextEntry = FlavorTextEntry(
            "Bla bla bla bla bla",
            Language("en"),
            Version("Diamon")
        )
    )
}


