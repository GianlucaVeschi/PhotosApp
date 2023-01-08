package com.gianlucaveschi.photosapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoDetailScreen(photoDetail: PhotoItemUiModel) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = rememberImagePainter(photoDetail.thumbnailUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(size = 16.dp))
            Text(text = "ID: ${photoDetail.id}")
            Spacer(modifier = Modifier.size(size = 16.dp))
            Text(text = "Title  ${photoDetail.title}")
            Spacer(modifier = Modifier.size(size = 16.dp))
            Text(text = "Album  ${photoDetail.albumId}")
        }
    }
}