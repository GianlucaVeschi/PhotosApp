package com.gianlucaveschi.photosapp.presentation.model.mapper

import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel

fun PhotoItem.mapToUiModel(): PhotoItemUiModel = this.run {
    PhotoItemUiModel(
        albumId = this.albumId,
        id = this.id,
        thumbnailUrl = this.thumbnailUrl,
        title = this.title,
        url = this.url
    )
}

fun List<PhotoItem>.mapToUiModel(): List<PhotoItemUiModel> = this.run {
    this.map { it.mapToUiModel() }
}
