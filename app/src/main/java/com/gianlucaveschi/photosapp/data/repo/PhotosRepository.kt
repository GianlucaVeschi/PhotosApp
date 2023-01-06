package com.gianlucaveschi.photosapp.data.repo

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel

interface PhotosRepository {
    suspend fun getPhotosList(): List<PhotoItemApiModel>?
    suspend fun getPhotoItem(photoId: Int): PhotoItemApiModel?
}