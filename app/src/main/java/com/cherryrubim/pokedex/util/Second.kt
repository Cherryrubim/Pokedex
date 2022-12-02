package com.cherryrubim.pokedex.util

fun <T> List<T>.secondOrNull(): T? {
    return if (1 <= lastIndex) get(1) else null
}