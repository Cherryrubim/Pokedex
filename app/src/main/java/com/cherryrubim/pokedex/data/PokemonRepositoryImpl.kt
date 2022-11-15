package com.cherryrubim.pokedex.data

import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.cherryrubim.pokedex.data.remote.model.PokemonResponseBody
import com.cherryrubim.pokedex.domain.repository.PokemonRepository
import com.cherryrubim.pokedex.util.Resource
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
            emit(Resource.Error(e))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        }

    }

    override fun getPokemon(name: String): Flow<Resource<PokemonInfo>> = flow {
        /*emit(Resource.Loading())
        try {
            emit(Resource.Success(api.getPokemon(name)))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }*/
    }
}