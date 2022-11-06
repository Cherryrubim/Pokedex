package com.sinusface.pokedex.util

sealed class Resource<out T>{
    data class Success<out T>(val data: T?): Resource<T>()
    class Loading<out T>: Resource<T>()
    data class Error<out T>(val exception: Exception): Resource<T>()
}
