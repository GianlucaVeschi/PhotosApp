package com.gianlucaveschi.photosapp.data.model

data class PhotoItemApiModel(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)