package com.cherryrubim.pokedex.util

fun String.removeDuplicateSpace(): String{
    return replace("\\s+".toRegex(), " ")
}