package com.gianlucaveschi.photosapp.domain.interactors

import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.data.util.NetworkResult
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.domain.model.mapper.mapToDomain
import timber.log.Timber

class GetPhotosListUseCase(
    private val photosRepository: PhotosRepository
) {
    suspend operator fun invoke(): List<PhotoItem>? = try {
        when (val response = photosRepository.getPhotosList()) {
            is NetworkResult.Success -> response.data.mapToDomain()
            is NetworkResult.Error -> handleError(response.message.toString())
            is NetworkResult.Exception -> handleError(response.e.message.toString())
        }
    } catch (exception: Exception) {
        handleError(exception.toString())
    }

    private fun handleError(exception: String): List<PhotoItem>? {
        Timber.d("exception: $exception")
        return null
    }
}