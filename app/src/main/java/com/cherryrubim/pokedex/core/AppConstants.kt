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

package com.cherryrubim.pokedex.core

import android.app.Application
import com.cherryrubim.pokedex.R
import java.util.*
import javax.inject.Inject


object AppConstants  {

    /*API URL*/
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    const val IMAGE_POKEMON_URI = ""

    /*Limit of Pokemons gets from Api*/
    const val LIMIT_POKEMONS = 20

    var LANGUAGE = Locale.getDefault().language
}