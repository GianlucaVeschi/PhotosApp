package com.gianlucaveschi.photosapp.domain.di

import android.content.Context
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoDetailUseCase
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.domain.interactors.ObserveNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetPhotoItemListUseCase(
        photosRepository: PhotosRepository
    ) = GetPhotosListUseCase(
        photosRepository
    )

    @ViewModelScoped
    @Provides
    fun provideGetPhotoDetailUseCase(
        photosRepository: PhotosRepository
    ) = GetPhotoDetailUseCase(
        photosRepository
    )

    @ViewModelScoped
    @Provides
    fun provideObserveNetworkUseCase(
        @ApplicationContext context : Context
    ) : ConnectivityObserver = ObserveNetworkUseCase(
        context
    )
}