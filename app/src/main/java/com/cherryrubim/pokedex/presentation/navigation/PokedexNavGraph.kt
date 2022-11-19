package com.cherryrubim.pokedex.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cherryrubim.pokedex.presentation.screen.pokemoninfo.PokemonInfo
import com.cherryrubim.pokedex.presentation.screen.pokemonlist.PokemonList

@Composable
fun PokedexNavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Screen.PokemonList.route
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){
        composable(Screen.PokemonList.route){
            /*PokemonList{ index ->
                Log.i("PokedexNavGraph", "Index: $index")
                navHostController.navigate("pokemonInfo/$index")
            }*/
        }

        composable(Screen.PokemonInfo.route + "/{index}",
            arguments = listOf(
                navArgument("index"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            //PokemonInfo(index = it.arguments?.getInt("index"))
        }

    }

}