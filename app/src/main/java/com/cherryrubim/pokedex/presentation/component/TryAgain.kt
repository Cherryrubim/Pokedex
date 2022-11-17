package com.cherryrubim.pokedex.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage
import com.cherryrubim.pokedex.R
import com.cherryrubim.pokedex.ui.theme.Raleway
import com.cherryrubim.pokedex.ui.theme.SnolaxColor

@Composable
fun TryAgain(onClick: () -> Unit = {}) {

    Box(
        modifier = Modifier.padding(20.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box() {

                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(64.dp),
                    painter = painterResource(id = R.drawable.wifi_off_fill_48),
                    contentDescription = "An error has occured.",
                    tint = SnolaxColor
                )

                CoilImage(
                    modifier = Modifier
                        .size(214.dp)
                        .align(Alignment.Center),
                    imageModel = { R.drawable.snorlax },
                    previewPlaceholder = R.drawable.snorlax
                )
            }

            Text(
                "Ooops!!",
                fontFamily = Raleway,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.size(18.dp))

            Text(
                "An error has occured.",
                fontFamily = Raleway,
                fontSize = 16.sp
            )
            Text(
                text = "Don’t worry, Snorlax will try to fix it.",
                fontFamily = Raleway,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.size(30.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .border(
                        BorderStroke(2.dp, color = SnolaxColor),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .clickable { onClick() }
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 5.dp, bottom = 8.dp, start = 20.dp, end = 20.dp),
                    text = "Try Again",
                    fontFamily = Raleway,
                    fontSize = 20.sp,
                )
            }
        }


    }

}