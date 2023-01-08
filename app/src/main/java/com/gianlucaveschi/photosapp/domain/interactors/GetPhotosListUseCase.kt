package com.gianlucaveschi.photosapp.domain.interactors

import android.util.Log
import com.gianlucaveschi.photosapp.domain.model.mapper.mapToDomain
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.model.PhotoItem

class GetPhotosListUseCase(
    private val photosRepository: PhotosRepository
) {
    suspend operator fun invoke(): List<PhotoItem>? = try {
        photosRepository.getPhotosList()?.mapToDomain()
    } catch (exception : Exception){
        Log.d("Porcospino", "invoke: $exception")
        null
    }
}