package com.gianlucaveschi.photosapp.data.network

import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosService {

    @GET("/photos")
    suspend fun getPhotosList(): Response<List<PhotoItemApiModel>>

    @GET("/photos/{photoId}")
    suspend fun getPhotoItem(@Path("photoId") photoId: Int): Response<PhotoItemApiModel>
}