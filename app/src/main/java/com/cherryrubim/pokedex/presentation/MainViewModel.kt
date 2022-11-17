package com.cherryrubim.pokedex.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherryrubim.pokedex.PokemonState
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import com.cherryrubim.pokedex.util.Resource
import androidx.compose.runtime.setValue
import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.data.paging.PaginatorImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
            state = state.copy(isError = false)

            if(state.pokemonList.isEmpty()){
                state = state.copy(isLoading = it)
            }else{
                state = state.copy(isLoading = false)
                state = state.copy(isLoadingPager = it)
            }
        },
        onSuccess = { item, newKey ->
            state = state.copy(pokemonList = state.pokemonList + item.pokemonList)
            Log.i("MainViewModel", state.pokemonList.toString())
        },
        onError = {
            state = state.copy(isError = true)
        },
        getNextKey = { responseBody ->
            /*Check if count is null and if reached to end elements*/
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
            }
        }
    )

    init {
        getPokemonPagers()
    }

    fun getPokemonPagers(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonRepository.getPokemonList(0).collect { result ->

                when (result) {

                    is Resource.Success -> {
                        state =
                            PokemonState(pokemonList = result.data?.pokemonList ?: emptyList())
                    }

                    is Resource.Loading -> {
                        state = PokemonState(isLoading = true)
                    }

                    is Resource.Error -> {
                       // state = PokemonState(error = result.exception.toString())
                    }
                }
            }
        }
    }
}