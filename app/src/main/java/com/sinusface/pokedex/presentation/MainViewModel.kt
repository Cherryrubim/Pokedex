package com.sinusface.pokedex.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinusface.pokedex.PokemonState
import com.sinusface.pokedex.domain.repository.PokemonRepository
import com.sinusface.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {

    private val  _state = mutableStateOf(PokemonState())
    val state: State<PokemonState> = _state

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonRepository.getPokemonList(0).collect { result ->

                when (result) {

                    is Resource.Success -> {
                        _state.value =
                            PokemonState(pokemonList = result.data?.pokemonList ?: emptyList())
                    }

                    is Resource.Loading -> {
                        _state.value = PokemonState(loading = true)
                    }

                    is Resource.Error -> {
                        _state.value = PokemonState(error = result.exception.toString())
                    }
                }
            }
        }
    }
}