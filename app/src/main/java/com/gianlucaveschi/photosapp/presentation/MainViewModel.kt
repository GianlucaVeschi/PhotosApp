package com.gianlucaveschi.photosapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    init {
        GlobalScope.launch {
            val photos = photosRepository.getPhotosList()
            Log.d("INDIA", ": ${photos.body()} ")
        }
    }

    fun getPhotosList(){}
}