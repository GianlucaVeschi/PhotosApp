package com.gianlucaveschi.photosapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoItemListUseCase: GetPhotoItemListUseCase
) : ViewModel() {

    init {
        GlobalScope.launch {
            val photos = getPhotoItemListUseCase()
            Log.d("INDIA", ": $photos ")
        }
    }

    fun getPhotosList(){}
}