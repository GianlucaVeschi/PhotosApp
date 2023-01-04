package com.gianlucaveschi.photosapp.domain.di

import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoItemListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetPhotoItemListUseCase(
        photosRepository: PhotosRepository
    ) = GetPhotoItemListUseCase(
        photosRepository
    )

}