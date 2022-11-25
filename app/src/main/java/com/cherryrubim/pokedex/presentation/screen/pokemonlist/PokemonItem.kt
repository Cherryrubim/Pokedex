package com.cherryrubim.pokedex.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.skydoves.landscapist.coil.CoilImage
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.util.generateOnColor
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

//@Preview(device = Devices.NEXUS_5)
@Composable
fun PokemonItem(
    //@PreviewParameter(SamplePokemonProvider::class)
    index: Int?, // <-Must Fix null!!!!
    pokemon: Pokemon,
    onClick: (Pokemon, Color) -> Unit

    //Navigation Compose
    //onClick: (Int) -> Unit
) {

    val defaultDominanColor = MaterialTheme.colorScheme.surfaceVariant

/*    var palette by remember {
        mutableStateOf<Palette?>(null)
    }*/

    val dominanColor = remember {
        mutableStateOf(defaultDominanColor)
    }

    val textColor = remember {
        mutableStateOf(Color.Black)
    }

    Box(
        modifier = Modifier
            .aspectRatio(3F / 4F)
            .clip(RoundedCornerShape(15.dp))
            .drawBehind { drawRect(color = dominanColor.value) }
            .clickable { onClick(pokemon, dominanColor.value) }
            //.clickable { index?.let { onClick(it) } }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            TextCard(
                text = "#${index.toString().padStart(3, '0')}", fontSize = 16.sp, fontWeight = FontWeight.Light) { textColor.value }

            TextCard(
                text = pokemon.name.capitalize(), fontSize = 24.sp, fontWeight = FontWeight.Bold) { textColor.value }

            CoilImage(
                modifier = Modifier
                    .aspectRatio(1F / 1F)
                    .padding(10.dp),
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
/*                    +PalettePlugin { palette ->
                        palette.dominantSwatch?.rgb?.let { colorValue ->
                            dominanColor.value = Color(colorValue)
                            textColor.value = Color(colorValue).generateOnColor()
                            Log.i("CoilImage", "Call Executer Palette!!!")
                        }
                    }*/
                    +PalettePlugin(
                        imageModel = pokemon.getImageUrl(),
                        useCache = true,
                        paletteLoadedListener = { palette ->
                            palette.dominantSwatch?.rgb?.let { colorValue ->

                                //The solution must be Test. (????
                                if(Color(colorValue).luminance() < 0.1){
                                    palette.lightVibrantSwatch?.rgb?.let {lightVibrantColor ->
                                        dominanColor.value = Color(lightVibrantColor)
                                        textColor.value = Color(lightVibrantColor).generateOnColor()
                                    }
                                }else{
                                    dominanColor.value = Color(colorValue)
                                    textColor.value = Color(colorValue).generateOnColor()
                                }

/*                                dominanColor.value = Color(colorValue)
                                textColor.value = Color(colorValue).generateOnColor()
                                val luminance = Color(colorValue).luminance()
                                Log.i("CoilImage", "Index: $index, Luminance: $luminance")*/
                            }
                        }
                    )
                }
            )
            /*Box(
                modifier = Modifier
                    .aspectRatio(1F / 1F)
                    .padding(15.dp)
                    .align(CenterHorizontally)
                    .background(Color.Blue)
            ) {

            }*/
        }
    }

}

@Composable
private fun TextCard(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    colorProvider: () -> Color
) {
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = fontWeight,
        color = colorProvider()
    )
}

/*class SamplePokemonProvider : PreviewParameterProvider<Pokemon> {
    override val values: Sequence<Pokemon> =
        sequenceOf(
            Pokemon("Ivasaur", "")
        )
}*/
