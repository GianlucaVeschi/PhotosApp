package com.gianlucaveschi.photosapp.presentation.detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoDetailUseCase
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModel() {

    val photoDetail: MutableState<PhotoItem?> =
        mutableStateOf(null)

    fun getPhotoDetail(photoId : Int) {
        viewModelScope.launch {
            val photo = getPhotoDetailUseCase(photoId)
            photoDetail.value = photo
            Log.d("INDIA", ": $photo ")
        }
    }
}