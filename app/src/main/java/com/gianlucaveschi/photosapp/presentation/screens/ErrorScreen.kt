package com.gianlucaveschi.photosapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gianlucaveschi.photosapp.R

@Composable
fun ErrorScreen() {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(SpacingLevel5)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(3f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            CenterIllustration()

        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterTitle(content = "Unfortunately there was an error.")
            CenterSubtitle(
                content = "Check if you lost connection or if you are in flight mode. " +
                        "Your Data will automatically be retrieved once you are back online"
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {}
    }
}

@Composable
fun CenterSubtitle(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(top = SpacingLevel5)
    )
}

@Composable
fun CenterTitle(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(top = SpacingLevel5)
    )
}

@Composable
fun CenterIllustration() {
    val image = painterResource(id = R.mipmap.ic_error_foreground)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.sizeIn(maxHeight = SizeDefault)
    )

}

val SizeDefault = 200.dp
val SpacingLevel5 = 16.dp