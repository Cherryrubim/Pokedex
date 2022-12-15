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

package com.cherryrubim.pokedex.data.paging

import android.util.Log
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class PaginatorImpl<Key, Item>(
    private val initialKey: Key?,
    private inline val onloading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<Item>>,
    private inline val onError: (Throwable?) -> Unit,
    private inline val onSuccess: (Item, newKey: Key?) -> Unit,
    private inline val getNextKey: (Item) -> Key?,
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextItems() {

        /* Prevent another request if there is one in progress or if currentKey is null. */
        if (isMakingRequest || currentKey == null) return

        isMakingRequest = true
        Log.i("PaginatorImpl", "Key: $currentKey")
        onRequest(currentKey!!).collect { result ->
            when (result) {
                is Resource.Success -> {
                    /* Delay is only use for debug!!!!!!! */
                    //delay(5000)
                    result.data?.let {
                        currentKey = getNextKey(it)
                        onSuccess(it, currentKey) // <- CurrentKey is NextKey.
                        Log.i("Paginator", "onRequest Success!! Next Key is: $currentKey")
                    }
                    onloading(false)
                    isMakingRequest = false
                }
                is Resource.Loading -> {
                    onloading(true)
                    Log.i("Paginator", "onRequest loading...")
                }
                is Resource.Error -> {
                    onloading(false)
                    onError(result.exception)
                    isMakingRequest = false
                    Log.e("Paginator", "onRequest Error!! Next Key is: ${result.exception}")
                }
            }
        }
    }

    override suspend fun reset() {
        currentKey = initialKey
    }
}