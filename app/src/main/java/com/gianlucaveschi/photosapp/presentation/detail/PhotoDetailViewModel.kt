package com.gianlucaveschi.photosapp.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoDetailUseCase
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel
import com.gianlucaveschi.photosapp.presentation.model.mapper.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModel() {

    val photoDetail: MutableState<PhotoItemUiModel?> =
        mutableStateOf(null)

    fun getPhotoDetail(photoId: Int) {
        viewModelScope.launch {
            val photo = getPhotoDetailUseCase(photoId)
            val photoUiModel = photo?.mapToUiModel()
            photoDetail.value = photoUiModel
        }
    }
}