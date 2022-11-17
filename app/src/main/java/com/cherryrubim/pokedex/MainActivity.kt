package com.cherryrubim.pokedex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.cherryrubim.pokedex.presentation.component.TryAgain
import com.cherryrubim.pokedex.presentation.feature.PokemonItem

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
                    val isLastItemVisible by remember {
                        derivedStateOf {
                            listState.isLastItemVisible()
                        }
                    }

                    if(isLastItemVisible && !state.isLoadingPager){
                        Log.i("List", "Last Item is Visible!!")
                        viewmodel.getPokemonPagers()
                    }

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                        if(state.isLoading){
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
                            if(state.isLoadingPager){
                                item {
                                    Spacer(modifier = Modifier.size(10.dp))
                                }
                            }
                        }
                        
                        if(state.isLoadingPager){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .align(Alignment.BottomCenter),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        if(state.isError){
                            Log.i(TAG, "StateError")
                            TryAgain(onClick = {
                                Log.i(TAG, "TryAgain!!")
                            })
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        //PokemonItem(pokemonName = "Pikachu")
    }
}


fun LazyGridState.isLastItemVisible(): Boolean{
    val check = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1
    return check
}

fun LazyListState.isLastItemVisible(): Boolean{

    val check = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1 // <- Bug?, layoutInfo start with a element.
/*    Log.i("LazyListState", "Check lastItemVisibility: $check")
    Log.i("LazyListState", "LayoutInfo Visible Last Index: ${layoutInfo.visibleItemsInfo.lastOrNull()?.index}")
    Log.i("LazyListState", "LayoutInfo totalItems: ${ layoutInfo.totalItemsCount}")
    Log.i("LazyListState", "LayoutInfo Last Item Key: ${ layoutInfo.visibleItemsInfo.lastOrNull()?.key}")*/
    return check
}

