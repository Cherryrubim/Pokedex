package com.cherryrubim.pokedex.data

import android.util.Log
import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.mapper.toDomain
import com.cherryrubim.pokedex.data.local.mapper.toEntity
import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.domain.model.Pokemon
import com.cherryrubim.pokedex.domain.model.SpeciesInfo
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

    override fun getPokemon(name: String): Flow<Resource<PokemonInfo>> = flow {
        val TAG_FUNTION = "GetPokemon"

        emit(Resource.Loading())
        try {

            val localData = database.pokemonInfoDao().getPokemonInfo(id = name)
            if (localData == null) {
                val remoteData = api.getPokemon(name)
                Log.i(TAG, "Remote Data: ${remoteData}")

                database.pokemonInfoDao().InsertPokemonInfo(remoteData.toEntity())
                Log.i(TAG, "LocalData Save: ${database.pokemonInfoDao().getPokemonInfo(name)}")

                emit(Resource.Success(database.pokemonInfoDao().getPokemonInfo(name)?.toDomain()))
            }else{
                emit(Resource.Success(localData.toDomain()))
            }


            emit(Resource.Success(api.getPokemon(name)))
        } catch (e: IOException) {
            Log.e(TAG, "$TAG_FUNTION Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "$TAG_FUNTION Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }

    override fun getPokemonDescription(name: String): Flow<Resource<SpeciesInfo>> = flow {
        val TAG_FUNTION = "GetPokemonDescription"

        emit(Resource.Loading())
        try {
            Log.w(TAG, "$TAG_FUNTION Finish Delay...")
            emit(Resource.Success(api.getPokemonDescription(name)))
        } catch (e: IOException) {
            Log.e(TAG, "$TAG_FUNTION Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "$TAG_FUNTION Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }

    fun <T> request(TAG_FUNTION: String, data: T, DELAY_DEBUG: Long = 0) = flow {
        emit(Resource.Loading())
        try {
            //delay(DELAY_DEBUG)
            val result = data
            Log.e(TAG, "$TAG_FUNTION SUCCESS: ${result}")
            emit(Resource.Success(data))
        } catch (e: IOException) {
            Log.e(TAG, "$TAG_FUNTION Error IO: ${e}")
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            Log.e(TAG, "$TAG_FUNTION Error Http: ${e}")
            emit(Resource.Error(e))
        }
    }
}