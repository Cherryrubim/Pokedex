package com.cherryrubim.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity
import com.cherryrubim.pokedex.data.local.entity.PokemonInfoEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE id = :id")
    suspend fun getPokemonInfo(id: String): PokemonInfoEntity?

    @Query("DELETE FROM PokemonInfoEntity")
    suspend fun clearDB()

}