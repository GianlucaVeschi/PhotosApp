package com.gianlucaveschi.photosapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gianlucaveschi.photosapp.domain.model.PhotoItem

@Composable
fun PhotosListScreen(photos: List<PhotoItem>) {
    LazyColumn {
        items(photos) { photo ->
            val paddingModifier = Modifier.padding(10.dp)
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(
                    text = photo.title,
                    modifier = paddingModifier
                )
            }
        }
    }
}