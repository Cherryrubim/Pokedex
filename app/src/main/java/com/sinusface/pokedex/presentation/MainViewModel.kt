package com.sinusface.pokedex.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinusface.pokedex.PokemonState
import com.sinusface.pokedex.domain.repository.PokemonRepository
import com.sinusface.pokedex.util.Resource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.sinusface.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.sinusface.pokedex.data.paging.DefaultPaginator
import com.sinusface.pokedex.data.paging.PaginatorImpl
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
            pokemonRepository.getPokemonList(page)
        },
        onloading = {
            state = state.copy(isLoadingPager = it)
        },
        onSuccess = { item, newKey ->
            state = state.copy(pokemonList = state.pokemonList + item.pokemonList)
        },
        onError = {
            state = state.copy(error = it?.message.toString())
        },
        getnextKey = { responseBody ->
            /*Check if count is null and if reached to end elements*/
            val count = responseBody.count
            if (count != null) {
                if (page * LIMIT_POKEMONS <= count) {
                     page++
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
        //getPokemonList()
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
                        state = PokemonState(error = result.exception.toString())
                    }
                }
            }
        }
    }
}