package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import com.ramcosta.composedestinations.annotation.Destination

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
        contentAlignment =  Alignment.Center
    ){
        /*Log.i("PokemonInfo", "State Loading: ${state.isLoading}")*/
        Log.i("PokemonInfo", "State: ${state}")
        if(state.isLoading){
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
                with(speciesInfo){
                    val pokemonDescription = flavor_text_entries.find { flavorTextEntry ->
                        flavorTextEntry.language.name == "en"
                    }

                    pokemonDescription?.let {flavorTextEntry ->
                        val description = flavorTextEntry.flavor_text
                        Text(text = description, fontSize = 16.sp, color = SnolaxColor)
                    }

                }
            }

        }
    }

}