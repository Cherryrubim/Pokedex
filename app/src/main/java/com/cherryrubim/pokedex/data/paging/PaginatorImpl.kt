package com.cherryrubim.pokedex.data.paging

import android.util.Log
import com.cherryrubim.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

class PaginatorImpl<Key, Item>(
    private val initialKey: Key?,
    private inline val onloading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<Item>>,
    private inline val onError: (Throwable?) -> Unit,
    private inline val onSuccess: (Item, newKey: Key) -> Unit,
    private inline val getNextKey: (Item) -> Key?,
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextItems() {

        /* Prevent another request if there is one in progress
         * Prevent request if currentKey is null.
         */
        if (isMakingRequest && currentKey == null) return

        isMakingRequest = true
        onRequest(currentKey!!).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        currentKey = getNextKey(it)
                        onSuccess(it, currentKey!!)
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
                    onError(result.exception)
                    isMakingRequest = false
                    onloading(false)
                    Log.e("Paginator", "onRequest Error!! Next Key is: ${result.exception}")
                }
            }
        }
    }

    override suspend fun reset() {
        currentKey = initialKey
    }
}