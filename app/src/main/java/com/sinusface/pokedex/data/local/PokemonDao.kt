package com.sinusface.pokedex.data.local

import androidx.compose.ui.geometry.Offset
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sinusface.pokedex.data.remote.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonList(): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertIdk(): PagingSource<Int, Pokemon>

    @Query("DELETE FROM pokemonentity")
    suspend fun clearDB()

}