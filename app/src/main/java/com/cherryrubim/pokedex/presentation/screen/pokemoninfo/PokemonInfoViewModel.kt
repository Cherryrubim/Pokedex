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
import com.cherryrubim.pokedex.domain.model.idk.SpeciesInfo
import com.cherryrubim.pokedex.util.ResultZip
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val TAG = "PokemonInfoViewModel"

    var state by mutableStateOf(PokemonInfoState())
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

                Log.i(TAG, "State Loading PreFlow: ${state.isLoading}")
                state = state.copy(isLoading = true)
                pokemonInfoFlow.zip(pokemonDescriptionFlow) { pokemonInfoResult, pokemonDescriptionResult ->
                    Log.i(TAG, "State Loading in Flow: ${state.isLoading}")
                    return@zip ResultZip(pokemonInfoResult, pokemonDescriptionResult)
                }
                    .catch { e ->
                        Log.e(TAG, e.message.toString())
                        state = state.copy(isError = true)
                    }
                    .collect { resultZip ->
                        val pokemonInfoResource = resultZip.componentA
                        val pokemonDescriptionResource = resultZip.ComponentB

                        if (pokemonInfoResource is Resource.Success &&
                            pokemonDescriptionResource is Resource.Success
                        ) {
                            state = PokemonInfoState(
                                pokemonInfo = pokemonInfoResource.data,
                                pokemonDescription = pokemonDescriptionResource.data
                            )
                            Log.i(TAG, "State Loading Success Set New State: ${state.isLoading}")
                        }
                    }
            }
        }
    }
}