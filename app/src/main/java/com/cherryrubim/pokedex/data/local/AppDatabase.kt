package com.cherryrubim.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cherryrubim.pokedex.data.local.converters.StatsConverter
import com.cherryrubim.pokedex.data.local.converters.TypeConverter
import com.cherryrubim.pokedex.data.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true // <- ONLY DEBUGE USED
)
@TypeConverters(value = [TypeConverter::class, StatsConverter::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}