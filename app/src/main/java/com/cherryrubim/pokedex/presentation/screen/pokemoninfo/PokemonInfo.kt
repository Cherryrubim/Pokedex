package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.coil.CoilImage

@Destination
@Composable
fun PokemonInfo(
    pokemon: Pokemon,
    color: Int = 0,
    viewModel: PokemonInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*Log.i("PokemonInfo", "State Loading: ${state.isLoading}")*/
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
    }

}

@Preview
@Composable
fun test() {

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF93C9AD))
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TopBar()
            IndexAndPokemonTypeRow()
            NameAndPokemonType2Row()

            CoilImage(
                modifier = Modifier
                    .padding(10.dp)
                    .aspectRatio(1F / 1F),
                imageModel = { R.drawable.ivasaur },
                previewPlaceholder = R.drawable.ivasaur
            )

            PokemonDescriptor()
            WeightHeightRow()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Text(text = "Stats Cubex")
            }

        }


    }


}

@Preview(widthDp = 300)
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.ic_arrow_back_32), contentDescription = "back")
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.ic_favorite_fill_32),
                contentDescription = "favorite"
            )
        }
    }
}

@Preview(widthDp = 300)
@Composable
fun IndexAndPokemonTypeRow(index: String = "#000", type: String = "????") {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = index,
            fontFamily = Raleway,
            fontWeight = FontWeight.Light,
            fontSize = 32.sp
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(22.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, SnolaxColor), RoundedCornerShape(20.dp))
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

@Preview(widthDp = 300)
@Composable
fun NameAndPokemonType2Row(name: String = "Ivysaur", type2: String = "????") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(22.dp))
                .background(Color.White)
                .border(BorderStroke(1.dp, SnolaxColor), RoundedCornerShape(20.dp))
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

@Preview(showBackground = true, backgroundColor = 0x93C9AD)
@Composable
fun PokemonDescriptor() {

    val horizontalPadding = 15.dp
    val verticalPadding = 15.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.Center)
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = verticalPadding,
                    bottom = 8.dp
                ),
                text = stringResource(id = R.string.lorem_ipsum),
                fontFamily = Raleway
            )
        }

        Box(
            modifier = Modifier
                .offset(x = 14.dp, y = -12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
                .align(Alignment.TopStart)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                text = "Pokédex Diamond",
                color = SnolaxColor,
                fontFamily = Raleway,
            )
        }
    }


}

@Preview(
    showBackground = true,
    backgroundColor = 0x93C9AD
)
@Composable
fun WeightHeightRow(weight: String = "4.6 Kg", height: String = "0.7 m") {

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 34.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.weight_24),
                        contentDescription = "weight"
                    )
                    Text(
                        text = weight,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = "Weight",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 5.dp),
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 34.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_straighten_24_),
                        contentDescription = "straighten"
                    )
                    Text(
                        text = height,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = "Height",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}