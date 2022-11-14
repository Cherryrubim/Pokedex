package com.sinusface.pokedex.data.paging

import android.util.Log
import com.sinusface.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

class PaginatorImpl<Key, Item>(
    private val initialKey: Key?,
    private inline val onloading: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<Item>>,
    private inline val onError: (Throwable?) -> Unit,
    private inline val onSuccess: (Item, newKey: Key) -> Unit,
    private inline val getnextKey: (Item) -> Key?,
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextItems() {

        /*Prevent double Request Solicitud*/
        //if (isMakingRequest) return

        //isMakingRequest = true
        //*Update UI status Composable loading*//
        //onloading(true)


        /*Prevent double request and if currentKey is null, endreach*/
        if (isMakingRequest && currentKey != null) return

        isMakingRequest = true
        onRequest(currentKey!!).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        currentKey = getnextKey(it)
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


/*       val result = onRequest(currentKey)
        val items = result.getOrElse {
            onError(it)
            onloading(false)
            isMakingRequest = false
            return
        }
        currentKey = getnextKey(items)
        onSuccess(items, currentKey)
        onloading(false)*/
    }

    override suspend fun reset() {
        currentKey = initialKey
    }
}