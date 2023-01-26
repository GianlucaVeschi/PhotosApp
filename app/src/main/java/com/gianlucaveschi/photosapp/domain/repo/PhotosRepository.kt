package com.gianlucaveschi.photosapp.domain.repo

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.util.NetworkResult

interface PhotosRepository {
    suspend fun getPhotosList(): NetworkResult<List<PhotoItemApiModel>>
    suspend fun getPhotoItem(photoId: Int): NetworkResult<PhotoItemApiModel>
}