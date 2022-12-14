package com.cherryrubim.pokedex.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherryrubim.pokedex.PokemonState
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import androidx.compose.runtime.setValue
import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.data.paging.PaginatorImpl
import com.cherryrubim.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    var page = 0
    var state by mutableStateOf(PokemonState())
        private set

    val paginator = PaginatorImpl(
        initialKey = page,
        onRequest = { page ->
            //* Offset is Page * LIMIT_POKEMONS*//
            pokemonRepository.getPokemonList(page * LIMIT_POKEMONS)
        },
        onloading = {
            /*Restore Error state for Try Again*/
            state = state.copy(isErrorInEmptyList = false)
            state = state.copy(isErrorPageNextRequest = false)

            /*Check for Loading types*/
            if(state.pokemonList.isEmpty()){
                state = state.copy(isLoadingInEmptyList = it) // <- Loading for EmptyList.
            }else{
                state = state.copy(isLoadingInEmptyList = false)
                state = state.copy(isLoadingNextPage = it)  // <- Loading for Resquest Page.
            }
        },
        onSuccess = { item, _ ->
            state = state.copy(
                pokemonList = state.pokemonList + item,
                lastItemID = item.lastOrNull()?.name
            )
            Log.i("MainViewModel", state.pokemonList.toString())
        },
        onError = {
            if(state.pokemonList.isEmpty()){
                state = state.copy(isErrorInEmptyList = true) // <- Show TryAgain Composable if an error ocurred.
            }else{
                state = state.copy(isErrorPageNextRequest = true) // <- An error ocurred when resquest New Pages
            }
        },
        getNextKey = { pokemonList ->
            if(pokemonList.size < LIMIT_POKEMONS){
                Log.i("MainViewModel", "PokemonList Size: ${pokemonList.size}")
                Log.i("MainViewModel", "Page: ${page}")
                return@PaginatorImpl null
            }
            return@PaginatorImpl ++page

            //with Response Count Size = Api Pokemon MAX
            /*Check if count is null and if reached to end elements*//*
            val count = responseBody.count
            Log.w("MainViewModel", "getNextKey responseBody Count: $count")
            if (count != null) {
                if (page * LIMIT_POKEMONS <= count) {
                    return@PaginatorImpl ++page
                } else {
                    Log.w("MainViewModel", "ResponseBody count is NULL.")
                    return@PaginatorImpl null
                }
            } else {
                return@PaginatorImpl null
            }*/
        }
    )

    init {
        getPokemonList()
    }

    fun getPokemonList(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}