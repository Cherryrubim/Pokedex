package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PokemonInfo(
    pokemon: Pokemon, color: Int = 0
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment =  Alignment.Center){
        Text(text = pokemon.name, fontSize = 60.sp)
    }

}