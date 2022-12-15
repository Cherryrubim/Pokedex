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

import com.cherryrubim.pokedex.data.local.AppDatabase
import com.cherryrubim.pokedex.data.local.PokemonDao
import com.cherryrubim.pokedex.data.local.PokemonInfoDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun providerPokemonDao(appDatabase: AppDatabase): PokemonDao

    @Binds
    @Singleton
    abstract fun providerPokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao
}*/
