/*
 * Copyright (c) 2022. Designed and developed by Cherryrubim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cherryrubim.pokedex.data

import android.util.Log
import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.mapper.toDomain
import com.cherryrubim.pokedex.data.local.mapper.toEntity
import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.PokemonSpecies
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI,
    private val database: AppDatabase
    ) : PokemonRepository
{
    val TAG = "PokemonRepositoryImpl"

    override fun getPokemonList(page: Int): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())

        try {
            val localData = database.pokemonDao().getPokemonList(page)

            if (localData.isEmpty()) {
                val remoteData = api.getPokemonList(offtset = page).pokemonList.toEntity()
                Log.i(TAG, "Remote Data: ${remoteData}")

                database.pokemonDao().InsertPokemonList(remoteData)
                Log.i(TAG, "LocalData Save: ${database.pokemonDao().getPokemonList(page).toDomain()}")

                emit(Resource.Success(database.pokemonDao().getPokemonList(page).toDomain()))
            }else{
                emit(Resource.Success(localData.toDomain()))
            }

        } catch (e: IOException) {
            Log.e(TAG, "GetPokemonList Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "GetPokemonList Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }

    override fun getPokemonInfo(name: String): Flow<Resource<PokemonInfo>> = flow {
        val TAG_FUNTION = "GetPokemon"

        emit(Resource.Loading())
        try {

            val localData = database.pokemonInfoDao().getPokemonInfo(name = name)
            if (localData == null) {
                val remoteData = api.getPokemon(name)
                Log.i(TAG, "Remote Data: ${remoteData}")

                database.pokemonInfoDao().InsertPokemonInfo(remoteData.toEntity())
                Log.i(TAG, "LocalData Save: ${database.pokemonInfoDao().getPokemonInfo(name)}")

                emit(Resource.Success(database.pokemonInfoDao().getPokemonInfo(name)?.toDomain()))
            }else{
                emit(Resource.Success(localData.toDomain()))
            }

        } catch (e: IOException) {
            Log.e(TAG, "$TAG_FUNTION Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "$TAG_FUNTION Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }

    override fun getPokemonDescription(name: String): Flow<Resource<PokemonSpecies>> = flow {
        val TAG_FUNTION = "GetPokemonDescription"

        emit(Resource.Loading())
        try {

            val localData = database.pokemonSpeciesDao().getPokemonDescription(name = name)
            if (localData == null) {
                val remoteData = api.getPokemonDescription(name)
                Log.i(TAG, "Remote Data: ${remoteData}")

                database.pokemonSpeciesDao().InsertPokemonDescription(remoteData.copy(name = name).toEntity())
                Log.i(TAG, "LocalData Save: ${database.pokemonSpeciesDao().getPokemonDescription(name = name)}")

                emit(Resource.Success(database.pokemonSpeciesDao().getPokemonDescription(name = name)?.toDomain()))
            }else{
                emit(Resource.Success(localData.toDomain()))
            }

        } catch (e: IOException) {
            Log.e(TAG, "$TAG_FUNTION Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "$TAG_FUNTION Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }
}