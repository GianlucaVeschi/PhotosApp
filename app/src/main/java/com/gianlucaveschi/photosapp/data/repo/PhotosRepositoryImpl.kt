package com.gianlucaveschi.photosapp.data.repo

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.network.PhotosService
import retrofit2.Response

class PhotosRepositoryImpl (
    private val photosService: PhotosService
) : PhotosRepository {

    override suspend fun getPhotosList(): List<PhotoItemApiModel>? =
        photosService.getPhotosList().body().takeIf { it != null }

    override suspend fun getPhotoItem(photoId: Int): PhotoItemApiModel? =
        photosService.getPhotoItem(photoId).body().takeIf { it != null }
}