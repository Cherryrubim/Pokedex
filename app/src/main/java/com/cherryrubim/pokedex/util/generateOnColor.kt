package com.cherryrubim.pokedex.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

//Extension Functions for calculate Text color
fun Color.generateOnColor()
        : Color {
    return if (luminance() > 0.5f) {
        Color.Black.copy(alpha = .8f)
    } else {
        Color.White
    }
}