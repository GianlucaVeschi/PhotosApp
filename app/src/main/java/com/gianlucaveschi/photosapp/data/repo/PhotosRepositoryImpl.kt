package com.gianlucaveschi.photosapp.data.repo

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.network.PhotosService
import retrofit2.Response

class PhotosRepositoryImpl (
    private val photosService: PhotosService
) : PhotosRepository {

    override suspend fun getPhotosList(): Response<List<PhotoItemApiModel>> =
        photosService.getPhotosList()

    override suspend fun getPhotoItem(photoId: String): Response<PhotoItemApiModel> =
        photosService.getPhotoItem(photoId)
}