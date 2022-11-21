package com.cherryrubim.pokedex.data

import android.util.Log
import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.model.Species
import com.cherryrubim.pokedex.domain.model.idk.SpeciesInfo
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(private val api: PokemonAPI) : PokemonRepository {

    val TAG = "PokemonRepositoryImpl"

    override fun getPokemonList(offset: Int): Flow<Resource<PokemonResponseBody>> = flow {

        emit(Resource.Loading())
        try {
            emit(Resource.Success(api.getPokemonList(offtset = offset)))
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
            Log.e(TAG, "$TAG_FUNTION Start Delay...")
            delay(4000)
            Log.e(TAG, "$TAG_FUNTION Finish Delay...")
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
            Log.e(TAG, "$TAG_FUNTION Start Delay...")
            delay(2000)
            Log.e(TAG, "$TAG_FUNTION Finish Delay...")
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