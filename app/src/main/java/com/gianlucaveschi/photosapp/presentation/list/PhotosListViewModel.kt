package com.gianlucaveschi.photosapp.presentation.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val getPhotosListUseCase: GetPhotosListUseCase
) : ViewModel() {

    val photosList: MutableState<List<PhotoItem>?> =
        mutableStateOf(listOf())

    fun getPhotosList() {
        viewModelScope.launch {
            val photos = getPhotosListUseCase()
            photosList.value = photos
            Log.d("INDIA", ": $photos ")
        }
    }
}