package com.cherryrubim.pokedex.util

sealed class Resource<out T>{
    data class Success<out T>(val data: T?, val DataComplement: Any? = null): Resource<T>()
    class Loading<out T>: Resource<T>()
    data class Error<out T>(val exception: Exception): Resource<T>()
}

data class ResultZip<out T, E>(val componentA: T?, val ComponentB: E?)
