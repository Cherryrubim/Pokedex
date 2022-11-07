package com.sinusface.pokedex.data.local

import androidx.compose.ui.geometry.Offset
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sinusface.pokedex.data.remote.model.Pokemon

@Dao
interface PokemonDao {

    suspend fun getPokemonList(offset: Int): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonList(): List<Pokemon>

}