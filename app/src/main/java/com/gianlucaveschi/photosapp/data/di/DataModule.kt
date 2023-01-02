package com.gianlucaveschi.photosapp.data.di

import com.gianlucaveschi.photosapp.data.network.PhotosService
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.data.repo.PhotosRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Singleton
        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }

        @Singleton
        @Provides
        fun provideHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS) //If Backend is really slow
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Singleton
        @Provides
        fun provideGithubRestService(okHttpClient: OkHttpClient): PhotosService {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(PhotosService::class.java)
        }

        @Singleton
        @Provides
        fun provideRepository(photosService: PhotosService): PhotosRepository {
            return PhotosRepositoryImpl(photosService)
        }
    }
}