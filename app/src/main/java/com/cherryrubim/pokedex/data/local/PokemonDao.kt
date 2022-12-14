package com.cherryrubim.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherryrubim.pokedex.core.AppConstants.LIMIT_POKEMONS
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonList(pokemonList: List<PokemonEntity>)

/*
    @Query("SELECT * FROM PokemonEntity WHERE page = :page")
    fun getPokemonList(page: Int): List<PokemonEntity>
*/

    @Query("SELECT * FROM PokemonEntity LIMIT :pageSize OFFSET :page")
    suspend fun getPokemonList(page: Int, pageSize: Int = LIMIT_POKEMONS): List<PokemonEntity>

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearDB()
}