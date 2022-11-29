package com.cherryrubim.pokedex.presentation.screen.pokemoninfo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherryrubim.pokedex.R

@Composable
fun TopBarCustom(modifier: Modifier = Modifier, color: Color = Color.White) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = { }
        ) {
            Icon(
                painterResource(
                    id = R.drawable.ic_arrow_back_32
                ),
                contentDescription = "back",
                modifier = Modifier.size(32.dp),
                tint = color
            )
        }

        IconButton(
            onClick = { }
        ) {
            Icon(
                painterResource(
                    id = R.drawable.ic_favorite_fill_32
                ),
                contentDescription = "favorite",
                modifier = Modifier.size(32.dp),
                tint = color
            )
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0x93C9AD, widthDp = 200)
@Composable
fun PreviewTopBarCustom(){
    TopBarCustom()
}