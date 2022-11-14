package com.sinusface.pokedex.data.paging

interface Paginator<Key, Item >{

    suspend fun loadNextItems()
    suspend fun reset()

}