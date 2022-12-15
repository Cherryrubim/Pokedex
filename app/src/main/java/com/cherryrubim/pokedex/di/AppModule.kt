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

package com.cherryrubim.pokedex.di

import android.app.Application
import androidx.room.Room
import com.cherryrubim.pokedex.core.AppConstants.BASE_URL
import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.converters.FlavorTextEntryConverter
import com.cherryrubim.pokedex.data.local.converters.StatsConverter
import com.cherryrubim.pokedex.data.local.converters.TypeConverter
import com.cherryrubim.pokedex.data.remote.PokemonAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providerMoshie(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun providerRoom(
        application: Application,
        typeConverter: TypeConverter,
        statsConverter: StatsConverter,
        flavorTextEntryConverter: FlavorTextEntryConverter
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "Pokemon.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeConverter)
            .addTypeConverter(statsConverter)
            .addTypeConverter(flavorTextEntryConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providerPokemonAPI(): PokemonAPI {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(PokemonAPI::class.java)
    }
}