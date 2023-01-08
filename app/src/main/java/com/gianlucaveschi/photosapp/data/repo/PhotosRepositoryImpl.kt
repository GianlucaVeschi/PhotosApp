package com.gianlucaveschi.photosapp.data.repo

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.network.PhotosService
import com.gianlucaveschi.photosapp.data.util.NetworkResult

class PhotosRepositoryImpl(
    private val photosService: PhotosService
) : PhotosRepository, BaseRepo() {

    override suspend fun getPhotosList(): NetworkResult<List<PhotoItemApiModel>> {
        return handleApi { photosService.getPhotosList() }
    }

    override suspend fun getPhotoItem(photoId: Int): NetworkResult<PhotoItemApiModel> {
        return handleApi { photosService.getPhotoItem(photoId) }
    }
}
