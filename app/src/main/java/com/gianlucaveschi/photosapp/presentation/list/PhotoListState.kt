package com.gianlucaveschi.photosapp.presentation.list

import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel

data class PhotosListState(
    val isLoading: Boolean = false,
    val photos: List<PhotoItemUiModel> = emptyList(),
    val error: String = ""
)