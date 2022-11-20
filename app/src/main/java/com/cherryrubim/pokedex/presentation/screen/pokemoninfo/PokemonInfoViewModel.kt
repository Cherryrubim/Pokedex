package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

import android.os.Parcelable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import com.cherryrubim.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.cherryrubim.pokedex.domain.model.PokemonInfo

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    var state by mutableStateOf(PokemonInfoState())
        private set

    init {
        getPokemon()
    }

    fun getPokemon(){

        savedStateHandle.get<Pokemon>("pokemon")?.name?.let { pokemonName ->

            viewModelScope.launch {
                pokemonRepository.getPokemon(pokemonName).collect{ result ->

                    when (result) {
                        is Resource.Success -> {
                            Log.i("PokemonInfoViewModel", "Success: ${result.data.toString()}")
                            state = state.copy(pokemonInfo = result.data)
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }

                        is Resource.Error -> {
                            state = state.copy(isError = true)
                        }
                    }
                }
            }
        }
    }
}