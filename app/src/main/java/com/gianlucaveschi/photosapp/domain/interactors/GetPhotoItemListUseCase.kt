package com.gianlucaveschi.photosapp.domain.interactors

import com.gianlucaveschi.photosapp.data.mapper.mapToDomain
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.model.PhotoItem

class GetPhotoItemListUseCase(
    private val photosRepository: PhotosRepository
) {
    suspend operator fun invoke(): List<PhotoItem>? {
        return photosRepository.getPhotosList()?.mapToDomain()
    }
}