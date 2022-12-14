package com.cherryrubim.pokedex.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cherryrubim.pokedex.domain.model.PokemonInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class StatsConverter @Inject constructor (private val moshi: Moshi) {

    val listType = Types.newParameterizedType(List::class.java, PokemonInfo.StatXX::class.java)
    val adapter = moshi.adapter<List<PokemonInfo.StatXX>>(listType)

    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.StatXX>?{
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromObject(listStats: List<PokemonInfo.StatXX>): String{
        return adapter.toJson(listStats)
    }
}