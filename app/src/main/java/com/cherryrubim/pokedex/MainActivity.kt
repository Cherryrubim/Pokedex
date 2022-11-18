package com.cherryrubim.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherryrubim.pokedex.presentation.MainViewModel
import com.cherryrubim.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import com.cherryrubim.pokedex.presentation.component.TryAgain
import com.cherryrubim.pokedex.presentation.feature.PokemonItem
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val state = viewmodel.state
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val listState = rememberLazyGridState()

/*                    val isPokemonListEmpty = remember(state.pokemonList) {
                        state.pokemonList.isEmpty()
                    }*/

                    val isLastItemVisible by remember {
                        derivedStateOf {
                            listState.isLastItemVisible()
                        }
                    }

                    if(isLastItemVisible && !state.isLoadingNextPage && !state.isErrorPageNextRequest){
                        Log.i(TAG, "State isError: ${state.isErrorPageNextRequest}")
                        Log.i("List", "Last Item is Visible!!")
                        viewmodel.getPokemonList()
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
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
                                PokemonItem(index = index+1, pokemon = item)
                            }

                            if(state.isLoadingNextPage){
                                item(span = { GridItemSpan(2)}) {
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
                                item(span = { GridItemSpan(2)}) {

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

                        /*LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState
                        ) {

                            items(
                                items = state.pokemonList.apply {
                                    Log.i(TAG, "LazyColumn List: ${this.toString()}")
                                },
                                key = { pokemon -> pokemon.name }) { pokemon ->
                                PokemonItem(pokemonName = pokemon.name)
                            }

                            if(state.isLoadingPager){
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }*/

                    }


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


fun LazyGridState.isLastItemVisible(): Boolean{
    val check = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1 // <- Bug?, layoutInfo start with a element.
    return check
}

fun LazyListState.isLastItemVisible(): Boolean{

    val check = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1 // <- Bug?, layoutInfo start with a element.
/*  Log.i("LazyListState", "Check lastItemVisibility: $check")
    Log.i("LazyListState", "LayoutInfo Visible Last Index: ${layoutInfo.visibleItemsInfo.lastOrNull()?.index}")
    Log.i("LazyListState", "LayoutInfo totalItems: ${ layoutInfo.totalItemsCount}")
    Log.i("LazyListState", "LayoutInfo Last Item Key: ${ layoutInfo.visibleItemsInfo.lastOrNull()?.key}")*/
    return check
}

