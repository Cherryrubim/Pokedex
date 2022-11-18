package com.cherryrubim.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cherryrubim.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import com.cherryrubim.pokedex.presentation.screen.pokemonlist.PokemonList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonList()
                }
            }
        }
    }
}

/*@Composable
fun PokemonItem(pokemonName: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(12.dp)
    ) {
        Text(text = pokemonName, modifier = Modifier.align(Alignment.CenterStart))
    }
}*/

@Preview()
@Composable
fun DefaultPreview() {
    PokedexTheme {
        //PokemonItem(pokemonName = "Pikachu")
    }
}
