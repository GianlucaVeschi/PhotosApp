package com.gianlucaveschi.photosapp.presentation.detail

import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel

data class PhotoDetailState(
    val isLoading: Boolean = false,
    val photo: PhotoItemUiModel? = null,
    val error: String = ""
)