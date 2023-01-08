package com.gianlucaveschi.photosapp.domain.interactors

import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.domain.model.mapper.mapToDomain
import timber.log.Timber

class GetPhotosListUseCase(
    private val photosRepository: PhotosRepository
) {
    suspend operator fun invoke(): List<PhotoItem>? = try {
        photosRepository.getPhotosList()?.mapToDomain()
    } catch (exception: Exception) {
        Timber.d("exception: $exception")
        null
    }
}