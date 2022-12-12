package com.cherryrubim.pokedex.presentation.screen.pokemoninfo

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
import com.cherryrubim.pokedex.util.ResultZip
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.zip

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val TAG = "PokemonInfoViewModel"

    var state by mutableStateOf(PokemonDetailState())
        private set

    init {
        getPokemon()
    }

    fun getPokemon() {
        savedStateHandle.get<Pokemon>("pokemon")?.name?.let { pokemonName ->
            Log.i(TAG, "SavedStateHandle: $pokemonName")
            viewModelScope.launch {



                val pokemonInfoFlow = pokemonRepository.getPokemon(pokemonName)
                val pokemonDescriptionFlow = pokemonRepository.getPokemonDescription(pokemonName)

                state = state.copy(isLoading = true)
                pokemonInfoFlow.zip(pokemonDescriptionFlow) { pokemonInfoResult, pokemonDescriptionResult ->
                    return@zip ResultZip(pokemonInfoResult, pokemonDescriptionResult)
                }
                    .catch { e ->
                        Log.e(TAG, e.message.toString())
                        state = state.copy(isError = true, isLoading = false)
                    }
                    .collect { resultZip ->
                        val pokemonInfoResource = resultZip.componentA
                        val pokemonDescriptionResource = resultZip.ComponentB

                        if (pokemonInfoResource is Resource.Success &&
                            pokemonDescriptionResource is Resource.Success
                        ) {
                            Log.i(TAG, pokemonInfoResource.data.toString())
                            Log.i(TAG, pokemonDescriptionResource.data?.flavor_text_entries?.find { it.version.name == "sword" && it.language.name == "es" }.toString())
                            state = PokemonDetailState(
                                pokemonInfo = pokemonInfoResource.data,
                                pokemonDescription = pokemonDescriptionResource.data,
                                isLoading = false
                            )
                        }
                    }
            }
        }
    }
}