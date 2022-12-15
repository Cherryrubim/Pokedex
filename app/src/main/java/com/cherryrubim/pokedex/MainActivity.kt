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

package com.cherryrubim.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.cherryrubim.pokedex.presentation.screen.NavGraphs
import com.cherryrubim.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import com.ramcosta.composedestinations.DestinationsNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    
                    DestinationsNavHost(navGraph = NavGraphs.root)


                    /*Navigation Compose*/
                    /*val navHostController = rememberNavController()
                    PokedexNavGraph(navHostController)*/
                }
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    PokedexTheme {
        //PokemonItem(pokemonName = "Pikachu")
    }
}
