package com.gianlucaveschi.photosapp.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.gianlucaveschi.photosapp.domain.model.PhotoItem

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotosListScreen(photos: List<PhotoItem>) {
    LazyColumn {
        items(photos) { photo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(90.dp)
                    .padding(10.dp)
                    .wrapContentHeight(align = Alignment.Top),
                shape = CutCornerShape(topEnd = 20.dp),
                elevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = rememberImagePainter(photo.thumbnailUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                    Spacer(modifier = Modifier.size(size = 16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        Text(text = photo.title)
                    }
                }
            }
        }
    }
}