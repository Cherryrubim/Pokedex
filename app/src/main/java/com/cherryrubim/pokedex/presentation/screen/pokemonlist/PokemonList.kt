package com.cherryrubim.pokedex.presentation.screen.pokemonlist

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherryrubim.pokedex.presentation.MainViewModel
import com.cherryrubim.pokedex.presentation.component.TryAgain
import com.cherryrubim.pokedex.presentation.screen.PokemonItem
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import com.cherryrubim.pokedex.presentation.screen.destinations.PokemonDetailDestination

import com.cherryrubim.pokedex.util.isLastItemVisible
import com.cherryrubim.pokedex.util.isLastItemVisibleByKey
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun PokemonList(
    navigator: DestinationsNavigator,
    viewmodel: MainViewModel = hiltViewModel(),
    //Navigation Compose
    // onNavigationToPokemonInfo: (Int) -> Unit
) {

    val TAG = "PokemonList"
    val state = viewmodel.state
    val listState = rememberLazyGridState()
    val isLastItemVisible by remember(state) {
        derivedStateOf {
            //listState.isLastItemVisible()
            listState.isLastItemVisibleByKey(key = state.lastItemID)
        }
    }

    /*Check conditional for New Request*/
    if(isLastItemVisible && !state.isLoadingNextPage && !state.isErrorPageNextRequest){
        Log.i(TAG, "Last Item is Visible!!")
        viewmodel.getPokemonList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
        ,
        contentAlignment = Alignment.Center
    ){

        if(state.isLoadingInEmptyList){
            CircularProgressIndicator()
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            itemsIndexed(
                state.pokemonList,
                key = { index, item -> item.name }
            ){ index, item ->
                PokemonItem(
                    index = index+1,
                    pokemon = item,
                    onClick = { pokemon, color ->
                       navigator.navigate(PokemonDetailDestination(index = index+1, pokemon = pokemon, color = color.toArgb()))
                    }
                    //Navigation Compose
                    //onClick = onNavigationToPokemonInfo
                )
            }

            if(state.isLoadingNextPage){
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            //Log.i(TAG, "State: ${state.pokemonList.isNotEmpty()}")

            if(state.isErrorPageNextRequest){
                item(span = { GridItemSpan(2) }) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(2F),
                            text = "⚠️  An error ha ocurrred"
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .border(
                                    BorderStroke(2.dp, color = SnolaxColor),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clickable { viewmodel.getPokemonList() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 5.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                                text = "Try Again", fontFamily = Raleway
                            )
                        }
                    }
                }
            }
        }

        if(state.isErrorInEmptyList){
            /*If PokemonList is Empty and an error ocurrend*/
            TryAgain(onClick = { viewmodel.getPokemonList() })
        }
    }


}