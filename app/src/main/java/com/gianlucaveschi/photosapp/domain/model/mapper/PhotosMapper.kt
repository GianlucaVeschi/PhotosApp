package com.gianlucaveschi.photosapp.domain.model.mapper

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.domain.model.PhotoItem

fun PhotoItemApiModel.mapToDomain() : PhotoItem = this.run {
    PhotoItem(
        albumId = this.albumId,
        id = this.id,
        thumbnailUrl = this.thumbnailUrl,
        title = this.title,
        url = this.url
    )
}

fun List<PhotoItemApiModel>.mapToDomain() : List<PhotoItem> = this.run {
    this.map { it.mapToDomain() }
}
