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
fun TopBarCustom(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            modifier = Modifier.size(48.dp),
            onClick = { onClick() }
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
            modifier = Modifier.size(48.dp),
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

@Preview(showBackground = true, backgroundColor = 0x93C9AD, widthDp = 200, showSystemUi = true)
@Composable
fun PreviewTopBarCustom(){
    TopBarCustom()
}